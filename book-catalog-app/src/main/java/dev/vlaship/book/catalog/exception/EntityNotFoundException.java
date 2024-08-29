package dev.vlaship.book.catalog.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String entity;
    private final long id;

    public EntityNotFoundException(String author, long authorId) {
        super("%s with id %d not found".formatted(author, authorId));
        this.entity = author;
        this.id = authorId;
    }
}
