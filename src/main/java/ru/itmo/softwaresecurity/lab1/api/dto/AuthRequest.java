package ru.itmo.softwaresecurity.lab1.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotNull
        @Size(min = 1)
        String login,
        @NotNull
        @Size(min = 6)
        String password
) { }
