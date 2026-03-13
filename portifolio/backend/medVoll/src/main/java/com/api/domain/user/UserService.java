package com.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service responsible for user management, including registration with
 * password encoding and uniqueness validation for sensitive data.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves a user by their email. Used primarily by the authentication
     * service.
     * 
     * @param email User's unique email.
     * @return Found user or throws exception.
     */
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the provided email."));
    }

    /**
     * Registers a new user after validating email and CPF uniqueness.
     * Encodes the password using BCrypt before persisting.
     * 
     * @param data DTO containing registration details.
     * @return The saved User entity.
     */
    @Transactional
    public User save(RegisterUser data) {
        // Validation: Prevent duplicate email entries
        if (repository.findByEmail(data.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        // Validation: Prevent duplicate CPF entries
        if (repository.findByCpf(data.getCpf()).isPresent()) {
            throw new RuntimeException("CPF already registered!");
        }

        // Encrypting the password before instantiation or saving
        String senhaCriptografada = passwordEncoder.encode(data.getPassword());

        // Using the custom constructor you defined in the User entity
        User newUser = new User(data, senhaCriptografada);

        return repository.save(newUser);
    }
}