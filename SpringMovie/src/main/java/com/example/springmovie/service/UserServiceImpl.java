package com.example.springmovie.service;

import com.example.springmovie.dto.request.UserLoginRequest;
import com.example.springmovie.dto.request.UserRegisterRequest;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.EncryptionService;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    @Override
    public User registerUser(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistsException {
        if (userRepository.findUserByEmailIgnoreCase(userRegisterRequest.email()).isPresent() || userRepository.findUserByUsernameIgnoreCase(userRegisterRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(userRegisterRequest.email());
        user.setUsername(userRegisterRequest.username());
        user.setPassword(encryptionService.encryptPassword(userRegisterRequest.password()));
        return userRepository.save(user);
    }

    public String loginUser(UserLoginRequest userLoginRequest) {
        Optional<User> optionalUser = userRepository.findUserByUsernameIgnoreCase(userLoginRequest.username());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(userLoginRequest.password(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
