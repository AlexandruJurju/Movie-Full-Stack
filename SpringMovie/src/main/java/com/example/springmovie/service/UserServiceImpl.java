package com.example.springmovie.service;

import com.example.springmovie.model.User;
import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // TODO: add upload image

    @Override
    public User findById(Long id) {
        // TODO: good exception
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id"));

    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

}
