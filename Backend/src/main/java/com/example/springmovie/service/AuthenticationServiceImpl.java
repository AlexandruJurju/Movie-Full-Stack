package com.example.springmovie.service;

import com.example.springmovie.dto.LoginRequestDto;
import com.example.springmovie.dto.RegisterRequestDto;
import com.example.springmovie.dto.LoginResponseDto;
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


    public LoginResponseDto register(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException {
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
        String jwtToken = jwtService.generateToken(user);
        return new LoginResponseDto(jwtToken);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(),
                        loginRequestDto.password())
        );

        User user = userRepository.findUserByUsernameIgnoreCase(loginRequestDto.username())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(jwtToken);
    }
}
