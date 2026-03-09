package com.login.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public User save(RegisterUser data) {
        if (repository.findByEmail(data.getEmail()).isPresent()) {
            throw new RuntimeException("Este e-mail já está cadastrado!");
        }

        if (repository.findByCpf(data.getCpf()).isPresent()) {
            throw new RuntimeException("Este CPF já está cadastrado!");
        }

        User newUser = new User();
        newUser.setName(data.getName());
        newUser.setEmail(data.getEmail());
        newUser.setCpf(data.getCpf());

        newUser.setRole(data.getRole());

        newUser.setAddress(data.getAddress());

        String senhaCriptografada = passwordEncoder.encode(data.getPassword());
        newUser.setPassword(senhaCriptografada);

        return repository.save(newUser);
    }
}