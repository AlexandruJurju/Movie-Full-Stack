package com.example.springmovie.controller;

import com.example.springmovie.dto.UserDisplayDto;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user using ID")
    public ResponseEntity<UserDisplayDto> getUserById(@PathVariable("id") Long id) {
        Optional<UserDisplayDto> userOptional = userService.findUserById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user using email")
    public ResponseEntity<UserDisplayDto> getUserByEmail(@PathVariable("email") String email) {
        Optional<UserDisplayDto> userOptional = userService.findUserByEmail(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDisplayDto>> getAllUsers() {
        List<UserDisplayDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user using Id")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
