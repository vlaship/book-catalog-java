package dev.vlaship.book.catalog.repository;

import dev.vlaship.book.catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    Optional<User> findByName(@NonNull String name);

}
