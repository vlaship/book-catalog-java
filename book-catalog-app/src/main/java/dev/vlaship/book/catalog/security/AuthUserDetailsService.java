package dev.vlaship.book.catalog.security;

import lombok.RequiredArgsConstructor;
import dev.vlaship.book.catalog.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String name) {
        var user = userRepository.findByName(name)
            .orElseThrow(() -> new UsernameNotFoundException(name));
        return new AuthPrinciple(user);
    }
}
