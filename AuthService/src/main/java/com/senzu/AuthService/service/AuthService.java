package com.senzu.AuthService.service;

import com.senzu.AuthService.dtos.requests.LoginRequest;
import com.senzu.AuthService.dtos.requests.RegisterRequest;
import com.senzu.AuthService.dtos.responses.JwtResponse;

public interface AuthService {


    public void register(RegisterRequest request);
    public JwtResponse login(LoginRequest request);
}
