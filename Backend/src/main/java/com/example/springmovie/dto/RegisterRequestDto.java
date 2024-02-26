package com.example.springmovie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank
    @Size(min = 6, max = 32)
    private final String username;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 8)
    // pattern for min 8 characters, at least one letter, one number and one special character
    //        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private final String password;
}