package dev.vlaship.book.catalog.service;

import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.exception.EntityNotFoundException;
import dev.vlaship.book.catalog.mapper.BookMapper;
import dev.vlaship.book.catalog.model.Book;
import dev.vlaship.book.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @NonNull
    public CreateBookDto createBook(@NonNull CreateBookRequest request) {
        var book = mapper.mapCreate(request);
        var saved = repository.save(book);
        return mapper.mapCreate(saved);
    }

    public void deleteBook(long bookId) {
        repository.deleteById(bookId);
    }

    @NonNull
    public BookDto getBook(long bookId) {
        var book = find(bookId);
        return mapper.map(book);
    }

    @NonNull
    public Page<BookListDto> getBooks(@NonNull Pageable pageable) {
        var books = repository.findAll(pageable);
        var dtos = mapper.map(books.getContent());
        return new PageImpl<>(dtos, pageable, books.getTotalElements());
    }

    @NonNull
    public BookDto updateBook(long bookId, @NonNull UpdateBookRequest request) {
        var book = find(bookId);
        mapper.merge(request, book);
        var saved = repository.save(book);
        return mapper.map(saved);
    }

    @NonNull
    private Book find(long bookId) {
        return repository.findById(bookId)
            .orElseThrow(() -> new EntityNotFoundException("book", bookId));
    }
}
