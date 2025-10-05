package ru.itmo.softwaresecurity.lab1.domain.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    final String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public static UserRole getByName(String name){
        return Arrays.stream(UserRole.values()).filter(userRole -> userRole.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
