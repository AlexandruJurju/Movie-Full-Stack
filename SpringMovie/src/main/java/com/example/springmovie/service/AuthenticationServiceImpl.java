package com.example.springmovie.service;

import com.example.springmovie.dto.request.LoginRequest;
import com.example.springmovie.dto.request.RegisterRequest;
import com.example.springmovie.dto.response.LoginResponse;
import com.example.springmovie.enums.Role;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public LoginResponse register(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if (userRepository.findUserByEmailIgnoreCase(registerRequest.email()).isPresent() || userRepository.findUserByUsernameIgnoreCase(registerRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password())
        );

        User user = userRepository.findUserByUsernameIgnoreCase(loginRequest.username())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponse(jwtToken);
    }
}
