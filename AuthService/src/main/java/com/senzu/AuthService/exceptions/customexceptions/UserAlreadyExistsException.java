package com.senzu.AuthService.exceptions.customexceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String userName) {
        super("User with username '" + userName + "' already exists!");
    }
}
