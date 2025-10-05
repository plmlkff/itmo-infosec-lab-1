package ru.itmo.softwaresecurity.lab1.api.dto;

import jakarta.validation.constraints.NotNull;
import ru.itmo.softwaresecurity.lab1.domain.entity.Post;
import ru.itmo.softwaresecurity.lab1.domain.entity.User;

public record CreatePostRequest(
        @NotNull
        String name,
        @NotNull
        String subject,
        @NotNull
        String body,
        @NotNull
        String ownerLogin
) {
    public Post toDomain(){
        return new Post(name, subject, body);
    }
}
