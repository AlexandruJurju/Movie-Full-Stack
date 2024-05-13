package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.UserDto;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> findUserById(Long id);

    List<UserDto> findAllUsers();

    void deleteUserById(Long id) throws UserNotFoundException;

    Optional<UserDto> findUserByEmail(String email);

    UserDto save(User user);

    Optional<User> findUserByUsername(String username);

    boolean checkUserExistsUsingEmail(String email);

    boolean checkUserExistsUsingUsername(String username);
}
