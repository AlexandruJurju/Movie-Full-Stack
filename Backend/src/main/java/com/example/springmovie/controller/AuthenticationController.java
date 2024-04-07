package com.example.springmovie.controller;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.exception.UserNotFoundException;
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
public class AuthenticationController {

    // TODO: register give default image for user

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register a user to the site")
    public ResponseEntity<LoginResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException, UserNotFoundException {
        return new ResponseEntity<>(authenticationService.register(registerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login to the site")
    public ResponseEntity<LoginResponseDto> authentication(@RequestBody @Valid LoginRequestDto loginRequestDto) throws UserNotFoundException {
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/status")
    @Operation(summary = "Receive a JWT token and check if it's valid")
    public ResponseEntity<LoginResponseDto> authenticationStatus(@RequestBody @Valid String token) throws UserNotFoundException {
        return new ResponseEntity<>(authenticationService.status(token), HttpStatus.OK);
    }
}
