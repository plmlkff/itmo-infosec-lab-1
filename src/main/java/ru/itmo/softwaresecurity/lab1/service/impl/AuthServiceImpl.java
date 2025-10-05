package ru.itmo.softwaresecurity.lab1.service.impl;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.softwaresecurity.lab1.api.dto.AuthRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.AuthResponse;
import ru.itmo.softwaresecurity.lab1.api.dto.SignUpRequest;
import ru.itmo.softwaresecurity.lab1.config.JwtProperties;
import ru.itmo.softwaresecurity.lab1.domain.entity.User;
import ru.itmo.softwaresecurity.lab1.domain.entity.UserRole;
import ru.itmo.softwaresecurity.lab1.domain.repository.UserRepository;
import ru.itmo.softwaresecurity.lab1.security.entity.JwtUserDetails;
import ru.itmo.softwaresecurity.lab1.service.AuthService;
import ru.itmo.softwaresecurity.lab1.util.security.JwtUtil;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    @Override
    public Either<HttpStatus, AuthResponse> login(AuthRequest authRequest) {
        var dbUserOpt = userRepository.findUserByLogin(authRequest.login());
        if (dbUserOpt.isEmpty()) return Either.left(HttpStatus.NOT_FOUND);

        var dbUser = dbUserOpt.get();

        if (!passwordEncoder.matches(authRequest.password(), dbUser.getPassword())) return Either.left(HttpStatus.FORBIDDEN);

        var token = JwtUtil.createToken(JwtUserDetails.fromDomain(dbUser), jwtProperties);

        return Either.right(new AuthResponse(dbUser.getLogin(), token));
    }

    @Override
    @Transactional
    public Either<HttpStatus, AuthResponse> signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.login())) return Either.left(HttpStatus.METHOD_NOT_ALLOWED);

        var user = User.builder()
                .login(signUpRequest.login())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .firstName(signUpRequest.firstName())
                .surname(signUpRequest.surname())
                .roles(Set.of(UserRole.USER))
                .build();

        user = userRepository.save(user);

        var token = JwtUtil.createToken(JwtUserDetails.fromDomain(user), jwtProperties);

        return Either.right(new AuthResponse(user.getLogin(), token));
    }
}
