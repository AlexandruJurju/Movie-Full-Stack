package com.example.springmovie.service;

import com.example.springmovie.dto.UserRegisterRequest;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistsException {

        if (userRepository.findUserByEmailIgnoreCase(userRegisterRequest.email()).isPresent() || userRepository.findUserByUsernameIgnoreCase(userRegisterRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(userRegisterRequest.email());
        user.setUsername(userRegisterRequest.username());
        // TODO: encrypt password
        user.setPassword(userRegisterRequest.password());
        return userRepository.save(user);
    }
}
