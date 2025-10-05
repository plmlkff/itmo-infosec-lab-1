package ru.itmo.softwaresecurity.lab1.api.dto;

import ru.itmo.softwaresecurity.lab1.domain.entity.User;

public record UserResponse(
        String login,
        String firstName,
        String surname
) {
    public static UserResponse formDomain(User user){
        return new UserResponse(user.getLogin(), user.getFirstName(), user.getSurname());
    }
}
