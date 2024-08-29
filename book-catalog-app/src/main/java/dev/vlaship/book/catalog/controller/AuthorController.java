package dev.vlaship.book.catalog.controller;

import dev.vlaship.book.catalog.api.AuthorApi;
import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {

    private final AuthorService service;

    @Override
    public ResponseEntity<CreateAuthorDto> createAuthor(CreateAuthorRequest request) {
        var resp = service.createAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long authorId) {
        service.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AuthorDto> getAuthor(Long authorId) {
        var resp = service.getAuthor(authorId);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<Page> getAuthors(Pageable pageable) {
        var resp = service.getAuthors(pageable);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<AuthorDto> updateAuthor(Long authorId, UpdateAuthorRequest request) {
        var resp = service.updateAuthor(authorId, request);
        return ResponseEntity.ok(resp);
    }
}
