package dev.vlaship.book.catalog.service;

import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.exception.EntityNotFoundException;
import dev.vlaship.book.catalog.mapper.AuthorMapper;
import dev.vlaship.book.catalog.model.Author;
import dev.vlaship.book.catalog.repository.AuthorRepository;
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
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    @NonNull
    public CreateAuthorDto createAuthor(@NonNull CreateAuthorRequest request) {
        var author = mapper.map(request);
        var saved = repository.save(author);
        return mapper.mapCreate(saved);
    }

    public void deleteAuthor(long authorId) {
        repository.deleteById(authorId);
    }

    @NonNull
    public AuthorDto getAuthor(long authorId) {
        log.debug("gettingAuthor({})", authorId);
        var author = find(authorId);
        return mapper.map(author);
    }

    @NonNull
    public Page<AuthorListDto> getAuthors(@NonNull Pageable pageable) {
        var authors = repository.findAll(pageable);
        var content = mapper.map(authors.getContent());
        return new PageImpl<>(content, pageable, authors.getTotalElements());
    }

    @NonNull
    public AuthorDto updateAuthor(long authorId, @NonNull UpdateAuthorRequest request) {
        var author = find(authorId);
        mapper.merge(request, author);
        var saved = repository.save(author);
        return mapper.map(saved);
    }

    private Author find(long authorId) {
        return repository.findById(authorId)
            .orElseThrow(() -> new EntityNotFoundException("author", authorId));
    }
}
