package com.example.springmovie.controller;

import com.example.springmovie.dto.response.LoginResponse;
import com.example.springmovie.dto.request.UserLoginRequest;
import com.example.springmovie.dto.request.UserRegisterRequest;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        try {
            return new ResponseEntity<>(userService.registerUser(userRegisterRequest), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        String jwt = userService.loginUser(userLoginRequest);
        if (jwt == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            LoginResponse loginResponse = new LoginResponse(jwt);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
    }

}
