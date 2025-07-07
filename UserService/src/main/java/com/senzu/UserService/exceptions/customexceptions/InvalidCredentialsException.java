package com.senzu.UserService.exceptions.customexceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super("Invalid username or password!");
    }
}
