package com.senzu.AuthService.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {


    private String userName;
    private String password;


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
