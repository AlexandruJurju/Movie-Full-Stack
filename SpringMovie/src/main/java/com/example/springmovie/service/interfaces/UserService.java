package com.example.springmovie.service.interfaces;

import com.example.springmovie.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User findById(Long id);

    UserDetailsService userDetailsService();
}
