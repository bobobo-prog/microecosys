package com.senzu.UserService.service.impl;

import com.senzu.UserService.entity.User;
import com.senzu.UserService.repository.UserRepository;
import com.senzu.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {

        List<User> data = new ArrayList<>();
        try {

            Optional<List<User>> optData = Optional.of(data);

            optData = userRepository.getAllUsers();
            if (optData.isPresent())
            {
                data = optData.get();
            }

        }
        catch (Exception e)
        {
            System.out.println(e);

        }

    return data;

    }
}
