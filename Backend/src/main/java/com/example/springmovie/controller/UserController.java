package com.example.springmovie.controller;

import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    // TODO: get users should not show passwords -> DTO
    // TODO: delete user -> cascade

    private final UserService userService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user using ID")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user using email")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);

    }

    // TODO: get all users should not return user password?
    @GetMapping("")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user using Id")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
