package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.exception.UserNotFoundException;

public interface AuthenticationService {

    LoginResponseDto register(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException, UserNotFoundException;

    LoginResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException;

    LoginResponseDto status(String token) throws UserNotFoundException;
}
