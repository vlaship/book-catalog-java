package dev.vlaship.book.catalog.repository;

import dev.vlaship.book.catalog.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @NonNull
    Optional<User> findByName(@NonNull String name);

}
