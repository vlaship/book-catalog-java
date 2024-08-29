package dev.vlaship.book.catalog.service;

import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.mapper.BookMapper;
import dev.vlaship.book.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @NonNull
    public CreateBookDto createBook(@NonNull CreateBookRequest request) {
        return null;
    }

    public void deleteBook(long bookId) {
    }

    @NonNull
    public BookDto getBook(long bookId) {
        return null;
    }

    @NonNull
    public Page<BookListDto> getBooks(@NonNull Pageable pageable) {
        return null;
    }

    @NonNull
    public BookDto updateBook(long bookId, @NonNull UpdateBookRequest request) {
        return null;
    }
}
