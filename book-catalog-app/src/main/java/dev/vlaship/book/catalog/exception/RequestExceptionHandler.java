package dev.vlaship.book.catalog.exception;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ProblemDetail> handling(final Exception e) {
        var problemDetail = switch (e) {
            case MethodArgumentNotValidException ex -> ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            case PropertyReferenceException ex -> ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            case BadCredentialsException ex -> ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
            case JwtAuthenticationException ex -> ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            case AccessDeniedException ex -> ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            case null, default -> ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, getDetail(e));
        };

        return ResponseEntity
                .status(problemDetail.getStatus())
                .body(problemDetail);
    }

    private String getDetail(Exception e) {
        return e != null ? e.getMessage() : HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }
}
