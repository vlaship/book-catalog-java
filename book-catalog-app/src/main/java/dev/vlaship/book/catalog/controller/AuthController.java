package dev.vlaship.book.catalog.controller;

import dev.vlaship.book.catalog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import dev.vlaship.book.catalog.api.AuthApi;
import dev.vlaship.book.catalog.dto.LoginRequest;
import dev.vlaship.book.catalog.dto.LoginResponse;
import dev.vlaship.book.catalog.dto.SignupRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UserService service;

    @Override
    public ResponseEntity<Void> signup(SignupRequest request) {
        service.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        var resp = service.login(request);
        return ResponseEntity.ok(resp);
    }
}
