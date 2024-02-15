package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.UserRegisterRequest;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;

public interface UserService {
    public User registerUser(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistsException;
}
