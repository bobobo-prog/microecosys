package com.senzu.UserService.controller;

import com.senzu.UserService.entity.User;
import com.senzu.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserDataController {


    private final UserService userService;

    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {

        List<User> data = new ArrayList<>();
        data = userService.getUsers();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(data, HttpStatus.OK);
        return responseEntity;

    }




}
