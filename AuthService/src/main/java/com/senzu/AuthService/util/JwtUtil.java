package com.senzu.AuthService.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private static final long EXPIRATION_DATE = 1000 * 60 * 60;

    private final Key key;

    public JwtUtil(Key key) {
        this.key = key;
    }

    public String generateToken(String userName)
    {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String token)
    {
        try{

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        }
        catch (JwtException | IllegalArgumentException e)
        {
            return false;
        }
    }


    public String extractUserName(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }






}
