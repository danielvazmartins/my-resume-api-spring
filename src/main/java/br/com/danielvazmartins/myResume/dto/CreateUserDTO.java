package br.com.danielvazmartins.myResume.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
    @NotBlank
    String name,

    @Email
    String email,

    @Size(min = 6, max = 10)
    String password
) {}
