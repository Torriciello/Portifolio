package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.api.domain.user.User;
import com.api.domain.user.auth.LoginData;
import com.api.infra.security.TokenJWTData;
import com.api.infra.security.TokenService;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling authentication-related requests.
 */
@CrossOrigin(origins = "*") // Allows requests from any origin (Update this for production security)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates a user and returns a JWT token.
     * * @param login Data transfer object containing user credentials (email and
     * password).
     * 
     * @return ResponseEntity containing the generated JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginData login) {
        // Creates a token object with the provided credentials for Spring Security to
        // process
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());

        // Triggers the authentication process; throws an exception if credentials are
        // invalid
        var authentication = manager.authenticate(authenticationToken);

        // Generates the JWT token for the successfully authenticated user
        var token = tokenService.gerarToken((User) authentication.getPrincipal());

        // Returns the token wrapped in a DTO with an HTTP 200 OK status
        return ResponseEntity.ok(new TokenJWTData(token));
    }
}