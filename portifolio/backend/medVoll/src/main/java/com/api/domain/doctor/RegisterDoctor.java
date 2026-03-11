package com.api.domain.doctor;

import com.api.domain.address.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDoctor(

        @NotBlank String name,

        @NotBlank String cpf,

        @NotBlank String crm,

        @NotBlank String phone,

        @NotNull Especialidade especialidade,

        @NotNull @Valid Address address

) {
}