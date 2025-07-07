package com.senzu.UserService.exceptions;

import com.senzu.UserService.exceptions.customexceptions.InvalidCredentialsException;
import com.senzu.UserService.exceptions.customexceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex)
    {
        return buildErrorResponse(HttpStatus.CONFLICT,ex.getMessage());

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex)
    {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());

    }

    public ResponseEntity<?> handleGeneric(UserAlreadyExistsException ex)
    {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected error occurred");


    }

    private ResponseEntity<Map<String,Object>> buildErrorResponse(HttpStatus status, String message)
    {
        Map<String ,Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body,status);



    }





}
