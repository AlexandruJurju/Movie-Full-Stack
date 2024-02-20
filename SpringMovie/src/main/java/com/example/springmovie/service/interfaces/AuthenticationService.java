package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.request.LoginRequest;
import com.example.springmovie.dto.request.RegisterRequest;
import com.example.springmovie.dto.response.LoginResponse;
import com.example.springmovie.exception.UserAlreadyExistsException;

public interface AuthenticationService {

    LoginResponse register(RegisterRequest registerRequest) throws UserAlreadyExistsException;

    LoginResponse login(LoginRequest loginRequest);
}
