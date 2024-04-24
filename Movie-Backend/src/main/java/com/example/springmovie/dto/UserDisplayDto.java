package com.example.springmovie.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springmovie.model.User}
 */
public record UserDisplayDto(String username, String profilePictureUrl) implements Serializable {
}