package com.example.springmovie.repositories;

import com.example.springmovie.enums.Role;
import com.example.springmovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameIgnoreCase(String username);

    Optional<User> findUserByEmailIgnoreCase(String email);

    List<User> findUserByRole(Role role);

}
