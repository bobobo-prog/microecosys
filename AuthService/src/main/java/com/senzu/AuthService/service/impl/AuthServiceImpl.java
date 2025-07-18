package com.senzu.AuthService.service.impl;

import com.senzu.AuthService.dtos.requests.LoginRequest;
import com.senzu.AuthService.dtos.requests.RegisterRequest;
import com.senzu.AuthService.dtos.responses.JwtResponse;
import com.senzu.AuthService.entity.User;
import com.senzu.AuthService.exceptions.customexceptions.InvalidCredentialsException;
import com.senzu.AuthService.exceptions.customexceptions.UserAlreadyExistsException;
import com.senzu.AuthService.repository.UserRepository;
import com.senzu.AuthService.service.AuthService;
import com.senzu.AuthService.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passEnco;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepo, PasswordEncoder passEnco, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passEnco = passEnco;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest request) {

        Optional<User> existing = userRepo.findByUserName(request.getUserName());
        if(existing.isPresent())
        {
            throw new UserAlreadyExistsException(request.getUserName());
        }

        User user = new User(request.getUserName(),passEnco.encode(request.getPassword()),"ROLE_USER");
        userRepo.save(user);

    }

    @Override
    public JwtResponse login(LoginRequest request) {


         User user = userRepo.findByUserName(request.getUserName())
                 .orElseThrow(InvalidCredentialsException::new);

         boolean res = passEnco.matches(request.getPassword(),user.getPassword());


         if(res!=true)
         {
             throw new InvalidCredentialsException();
         }

         String generatedToken =jwtUtil.generateToken(user.getUserName());
        return new JwtResponse(generatedToken);

    }
}
