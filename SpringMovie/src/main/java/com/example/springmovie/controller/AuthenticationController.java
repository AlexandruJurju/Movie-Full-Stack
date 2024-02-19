package com.example.springmovie.controller;

import com.example.springmovie.dto.request.LoginRequest;
import com.example.springmovie.dto.request.RegisterRequest;
import com.example.springmovie.dto.response.LoginResponse;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            // TODO: register give default image
            return new ResponseEntity<>(authenticationService.register(registerRequest), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authentication(@RequestBody @Valid LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
