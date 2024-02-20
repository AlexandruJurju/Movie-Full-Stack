package com.example.springmovie.service.interfaces;

import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User findById(Long id) throws UserNotFoundException;

    List<User> findAllUsers();

    void deleteUserById(Long id) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;

    UserDetailsService userDetailsService();
}
