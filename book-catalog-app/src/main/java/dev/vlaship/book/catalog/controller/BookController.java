package dev.vlaship.book.catalog.controller;

import dev.vlaship.book.catalog.api.BookApi;
import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService service;

    @Override
    public ResponseEntity<CreateBookDto> createBook(CreateBookRequest request) {
        var resp = service.createBook(request);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long bookId) {
        service.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDto> getBook(Long bookId) {
        var resp = service.getBook(bookId);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<Page> getBooks(Pageable pageable) {
        var resp = service.getBooks(pageable);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long bookId, UpdateBookRequest request) {
        var resp = service.updateBook(bookId, request);
        return ResponseEntity.ok(resp);
    }
}
