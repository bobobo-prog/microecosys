package com.senzu.UserService.service;

import com.senzu.UserService.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public List<User> getUsers();
}
