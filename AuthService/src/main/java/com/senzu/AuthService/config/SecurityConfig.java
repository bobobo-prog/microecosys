package com.senzu.AuthService.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Key;

@Configuration
public class SecurityConfig {

    private static final String SECRET = "MYSECRETKEYMYSECRETKEYMYSECRETKEYMYSECRETKEY";

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Key jwtSigningKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register","/login").permitAll()
                        .anyRequest().authenticated()
                ).build();


    }




}
