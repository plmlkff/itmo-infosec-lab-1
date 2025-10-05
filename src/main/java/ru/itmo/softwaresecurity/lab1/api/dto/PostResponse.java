package ru.itmo.softwaresecurity.lab1.api.dto;

import ru.itmo.softwaresecurity.lab1.domain.entity.Post;

public record PostResponse(
        String name,
        String subject,
        String body,
        UserResponse owner
) {
    public static PostResponse fromDomain(Post post){
        return new PostResponse(post.getName(), post.getSubject(), post.getBody(), UserResponse.formDomain(post.getOwner()));
    }
}
