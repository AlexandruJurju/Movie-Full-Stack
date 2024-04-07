package com.example.springmovie.service;

import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //                return new UserDetailsService() {
        //            @Override
        //            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //                return userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //            }
        //        };
    }

}
