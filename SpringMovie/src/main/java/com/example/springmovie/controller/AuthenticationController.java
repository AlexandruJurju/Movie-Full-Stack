package com.example.springmovie.controller;

import com.example.springmovie.dto.request.LoginRequest;
import com.example.springmovie.dto.request.RegisterRequest;
import com.example.springmovie.dto.response.LoginResponse;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.service.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/auth")
// TODO: register give default image for user
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register a user to the site")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid RegisterRequest registerRequest) throws UserAlreadyExistsException {
        return new ResponseEntity<>(authenticationService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login to the site")
    public ResponseEntity<LoginResponse> authentication(@RequestBody @Valid LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    //    @GetMapping("/test")
    //    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal User user) {
    //        return new ResponseEntity<>(user, HttpStatus.OK);
    //    }
}
