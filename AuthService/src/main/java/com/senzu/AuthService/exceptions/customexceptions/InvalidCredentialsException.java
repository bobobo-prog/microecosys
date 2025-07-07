package com.senzu.AuthService.exceptions.customexceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super("Invalid username or password!");
    }
}
