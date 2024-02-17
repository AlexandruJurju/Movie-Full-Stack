package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.request.AuthenticationRequest;
import com.example.springmovie.dto.request.RegisterRequest;
import com.example.springmovie.dto.response.AuthenticationResponse;
import com.example.springmovie.exception.UserAlreadyExistsException;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest registerRequest) throws UserAlreadyExistsException;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
