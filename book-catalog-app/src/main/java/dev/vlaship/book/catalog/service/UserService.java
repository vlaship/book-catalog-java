package dev.vlaship.book.catalog.service;

import dev.vlaship.book.catalog.dto.LoginRequest;
import dev.vlaship.book.catalog.dto.LoginResponse;
import dev.vlaship.book.catalog.dto.SignupRequest;
import dev.vlaship.book.catalog.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.vlaship.book.catalog.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @NonNull
    public LoginResponse login(@NonNull LoginRequest request) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var jwt = tokenService.generateToken(auth.getName());
        return new LoginResponse().token(jwt.getTokenValue());
    }

    public void signup(@NonNull SignupRequest request) {
        var encoded = passwordEncoder.encode(request.getPassword());
        request.setPassword(encoded);
        var user = User.builder()
                .name(request.getUsername())
                .password(encoded)
                .build();
        userRepository.save(user);
    }
}
