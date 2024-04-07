package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.exception.UserAlreadyExistsException;

public interface AuthenticationService {

    LoginResponseDto register(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException;

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
