package com.dhruv.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final WebClient userServiceWebClient;

    /**
     * Validates if a user exists in the system.
     * Returns true if user exists, false if not found (404).
     * Throws error for other HTTP errors.
     */
    public Mono<Boolean> validateUser(String userId) {
        log.info("calling user service for {}", userId);

            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(WebClientResponseException.class, e -> {
                        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                            return Mono.error(new RuntimeException("User not found: " + userId));
                        } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                            return Mono.error(new RuntimeException("Invalid userId: " + userId));
                        }
                        return Mono.error(new RuntimeException("Unexpected error validating user: " + userId));
                    });
    }

    /**
     * Registers a new user in the system.
     * Returns UserResponse on success, throws error on failure.
     */
    public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
        log.info("Calling user registration for {}", registerRequest.getEmail());
        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        log.error("Bad request during registration: {}", e.getMessage());
                        return Mono.error(new RuntimeException("Bad request during user registration: " + e.getMessage()));
                    }
                    log.error("Unexpected error during registration: {}", e.getMessage());
                    return Mono.error(new RuntimeException("Unexpected error during user registration: " + e.getMessage()));
                })
                .doOnSuccess(response -> log.info("Successfully registered user: {}", registerRequest.getEmail()))
                .doOnError(e -> log.error("Failed to register user {}: {}", registerRequest.getEmail(), e.getMessage()));
    }
}