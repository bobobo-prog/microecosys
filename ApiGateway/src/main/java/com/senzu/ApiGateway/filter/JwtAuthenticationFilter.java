package com.senzu.ApiGateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        HttpMethod method = exchange.getRequest().getMethod();

        System.out.println("========== JWT Filter triggered for path: " + path);
        if (path.startsWith("/auth/")) {
            System.out.println("Skipping JWT authentication for /auth/ path");
            return chain.filter(exchange);
        }

        System.out.println("Processing JWT authentication for protected path");

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            System.out.println("No valid Authorization header found");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);
        token  = token;

        try
        {


            Key key = Keys.hmacShaKeyFor("MYSECRETKEYMYSECRETKEYMYSECRETKEYMYSECRETKEY".getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();


            String userName = claims.getSubject();


            List<String> roles = claims.get("roles",List.class);
            List<SimpleGrantedAuthority> authorities = roles == null ? Collections.emptyList()
                    //otherwise
                    :roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,null,authorities);



            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(new SecurityContextImpl(authentication))));

        }
        catch (Exception e)
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();

        }










    }
}
