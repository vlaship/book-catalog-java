package dev.vlaship.book.catalog.repository;

import dev.vlaship.book.catalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
