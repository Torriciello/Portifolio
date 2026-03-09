package com.login.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.login.domain.user.User;
import com.login.domain.user.auth.LoginData;
import com.login.infra.security.TokenJWTData;
import com.login.infra.security.TokenService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginData login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var authentication = manager.authenticate(authenticationToken);

        var token = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(token));
    }
}
