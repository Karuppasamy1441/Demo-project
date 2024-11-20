package com.Booking.Application.system.Config;

import com.Booking.Application.system.Entity.User;
import com.Booking.Application.system.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (isValidUser(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new AuthenticationException("Invalid credentials") {
            };
        }
    }

    private boolean isValidUser(String email, String password) {
        User user = userRepository.findByEmail(email).get();
        return user.getEmail().equals(email) && passwordEncoder.matches(password, user.getPassword());
    }
}
