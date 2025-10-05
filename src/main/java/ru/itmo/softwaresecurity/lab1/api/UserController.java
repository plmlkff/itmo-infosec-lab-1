package ru.itmo.softwaresecurity.lab1.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.softwaresecurity.lab1.api.dto.AuthRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.SignUpRequest;
import ru.itmo.softwaresecurity.lab1.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody
            @Valid
            AuthRequest request
    ){
        return authService.login(request).fold(
                status -> ResponseEntity.status(status.value()).build(),
                ResponseEntity::ok
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(
            @RequestBody
            SignUpRequest request
    ){
        return authService.signUp(request).fold(
                status -> ResponseEntity.status(status.value()).build(),
                ResponseEntity::ok
        );
    }
}
