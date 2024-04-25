package com.example.springmovie.service;

import com.example.springmovie.dto.UserDto;
import com.example.springmovie.mappers.UserMapper;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto save(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    // TODO use dto
    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsernameIgnoreCase(username);
    }

    @Override
    public boolean checkUserExistsUsingEmail(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Override
    public boolean checkUserExistsUsingUsername(String username) {
        return findUserByUsername(username).isPresent();
    }
}
