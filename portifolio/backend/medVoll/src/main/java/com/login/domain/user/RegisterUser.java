package com.login.domain.user;

import com.login.domain.address.Address;
import com.login.domain.role.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUser {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    @NotNull
    public Role role;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private Address address;

}