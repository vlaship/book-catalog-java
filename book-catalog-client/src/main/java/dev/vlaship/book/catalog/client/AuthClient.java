package dev.vlaship.book.catalog.client;

import dev.vlaship.book.catalog.api.AuthApi;
import dev.vlaship.book.catalog.client.config.RestClientProperties;
import dev.vlaship.book.catalog.dto.LoginRequest;
import dev.vlaship.book.catalog.dto.LoginResponse;
import dev.vlaship.book.catalog.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class AuthClient implements AuthApi {

    private final RestClientProperties clientProperties;
    private final RestClient restClient;

    @Override
    public ResponseEntity<Void> signup(SignupRequest request) {
        var url = UriComponentsBuilder.newInstance()
                .path(clientProperties.path())
                .path("/signup")
                .build()
                .toString();
        return restClient
                .post()
                .uri(url)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        var url = UriComponentsBuilder.newInstance()
                .path(clientProperties.path())
                .path("/login")
                .build()
                .toString();
        return restClient
                .post()
                .uri(url)
                .retrieve()
                .toEntity(LoginResponse.class);
    }
}
