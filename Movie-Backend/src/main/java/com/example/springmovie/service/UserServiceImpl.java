package com.example.springmovie.service;

import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // TODO: add upload image

    @Override
    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(id);
    }


    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findUserByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UserNotFoundException("User with email " + username + " not found"));
    }

    @Override
    public boolean checkUserExistsUsingEmail(String email) {
        try {
            findUserByEmail(email);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean checkUserExistsUsingUsername(String username) {
        try {
            findUserByUsername(username);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
