package com.senzu.AuthService.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class LoginRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
