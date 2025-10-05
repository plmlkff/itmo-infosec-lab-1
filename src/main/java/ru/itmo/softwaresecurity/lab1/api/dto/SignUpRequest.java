package ru.itmo.softwaresecurity.lab1.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotNull
        @Size(min = 1)
        String login,
        @NotNull
        @Size(min = 1)
        String firstName,
        @NotNull
        @Size(min = 1)
        String surname,
        @NotNull
        @Size(min = 6)
        String password
) { }
