package com.example.springmovie.service.interfaces;

import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id) throws UserNotFoundException;

    List<User> findAllUsers();

    void deleteUserById(Long id) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;

    User save(User user);

    User findUserByUsername(String username) throws UserNotFoundException;

    boolean checkUserExistsUsingEmail(String email);

    boolean checkUserExistsUsingUsername(String username);
}
