package br.com.danielvazmartins.myResume.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestLoginDTO(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {}
