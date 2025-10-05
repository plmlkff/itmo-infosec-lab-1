package ru.itmo.softwaresecurity.lab1.api.dto;

import jakarta.validation.constraints.NotNull;

public record AuthResponse(
        @NotNull
        String login,
        @NotNull
        String token
) {
}
