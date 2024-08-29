package dev.vlaship.book.catalog.security;

import lombok.RequiredArgsConstructor;
import dev.vlaship.book.catalog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String name) {
        var user = userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
        return new AuthPrinciple(user);
    }
}
