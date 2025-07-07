package com.senzu.AuthService.controller;


import com.senzu.AuthService.dtos.requests.LoginRequest;
import com.senzu.AuthService.dtos.requests.RegisterRequest;
import com.senzu.AuthService.dtos.responses.JwtResponse;
import com.senzu.AuthService.service.AuthService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Logs in and returns a JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.login(request);
        return ResponseEntity.ok(jwtResponse);
    }
}
