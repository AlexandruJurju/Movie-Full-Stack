package com.example.springmovie.service;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.enums.Role;
import com.example.springmovie.exception.UserAlreadyExistsException;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.AuthenticationService;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto register(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException, UserNotFoundException {
        log.info("Registering new user: {}", registerRequestDto.username());

        if (userService.findUserByEmail(registerRequestDto.email()) != null || userService.findUserByUsername(registerRequestDto.username()) != null) {
            throw new UserAlreadyExistsException();
        }


        User user = User.builder()
                .username(registerRequestDto.username())
                .email(registerRequestDto.email())
                .password(passwordEncoder.encode(registerRequestDto.password()))
                .role(Role.USER)
                .build();

        userService.save(user);
        log.info("User registered successfully: {}", registerRequestDto.username());

        String jwtToken = jwtService.generateToken(user);
        return new LoginResponseDto(registerRequestDto.username(), jwtToken);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException {
        log.info("Authenticating user: {}", loginRequestDto.username());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.username(),
                loginRequestDto.password())
        );

        User user = userService.findUserByUsername(loginRequestDto.username());

        log.info("User authenticated successfully: {}", loginRequestDto.username());
        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(loginRequestDto.username(), jwtToken);
    }

    @Override
    public LoginResponseDto status(String token) throws UserNotFoundException {
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userService.findUserByUsername(username);

        jwtService.isTokenValid(token, userDetails);

        return new LoginResponseDto(username, token);
    }
}
