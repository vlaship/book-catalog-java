package dev.vlaship.book.catalog.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import dev.vlaship.book.catalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @NonNull
    @EntityGraph(value = "Product.prices_categories", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findById(@NonNull Long id);
}
