package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.UserDisplayDto;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDisplayDto> findUserById(Long id);

    List<UserDisplayDto> findAllUsers();

    void deleteUserById(Long id) throws UserNotFoundException;

    Optional<UserDisplayDto> findUserByEmail(String email);

    UserDisplayDto save(User user);

    Optional<User> findUserByUsername(String username);

    boolean checkUserExistsUsingEmail(String email);

    boolean checkUserExistsUsingUsername(String username);
}
