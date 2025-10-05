package ru.itmo.softwaresecurity.lab1.service;

import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import ru.itmo.softwaresecurity.lab1.api.dto.AuthRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.AuthResponse;
import ru.itmo.softwaresecurity.lab1.api.dto.SignUpRequest;

public interface AuthService {
    Either<HttpStatus, AuthResponse> login(AuthRequest authRequest);

    Either<HttpStatus, AuthResponse> signUp(SignUpRequest signUpRequest);
}
