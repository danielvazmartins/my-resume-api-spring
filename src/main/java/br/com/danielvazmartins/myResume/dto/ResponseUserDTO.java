package br.com.danielvazmartins.myResume.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResponseUserDTO(
    String id,

    @NotBlank
    String name,

    @Email
    String email
) {}
