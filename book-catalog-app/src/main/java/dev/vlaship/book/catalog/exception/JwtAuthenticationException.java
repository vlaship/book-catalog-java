package dev.vlaship.book.catalog.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException() {
        super("Token is invalid");
    }
}
