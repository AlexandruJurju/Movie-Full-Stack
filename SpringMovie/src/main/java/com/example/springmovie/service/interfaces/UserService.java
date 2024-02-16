package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.request.UserLoginRequest;
import com.example.springmovie.dto.request.UserRegisterRequest;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;

public interface UserService {
    User registerUser(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistsException;
    String loginUser(UserLoginRequest userLoginRequest);
}
