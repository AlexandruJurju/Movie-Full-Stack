package com.example.springmovie.service;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.enums.Role;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.security.JWTService;
import com.example.springmovie.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto register(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException {
        log.info("Registering new user: " + registerRequestDto.username());

        if (userRepository.findUserByEmailIgnoreCase(registerRequestDto.email()).isPresent() || userRepository.findUserByUsernameIgnoreCase(registerRequestDto.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .username(registerRequestDto.username())
                .email(registerRequestDto.email())
                .password(passwordEncoder.encode(registerRequestDto.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        log.info("User registered successfully: " + registerRequestDto.username());
        String jwtToken = jwtService.generateToken(user);
        return new LoginResponseDto(jwtToken);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        log.info("Authenticating user: " + loginRequestDto.username());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(),
                        loginRequestDto.password())
        );

        User user = userRepository.findUserByUsernameIgnoreCase(loginRequestDto.username())
                .orElseThrow();

        log.info("User authenticated successfully: " + loginRequestDto.username());
        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(jwtToken);
    }
}
