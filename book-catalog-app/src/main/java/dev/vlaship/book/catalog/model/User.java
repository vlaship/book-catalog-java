package dev.vlaship.book.catalog.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Builder
public record User(
    @Id Long id,
    String name,
    String password
) {}
