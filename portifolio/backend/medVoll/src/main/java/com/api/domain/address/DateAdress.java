package com.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DateAdress(
        @NotBlank String cep,

        @NotBlank String publicPlace,

        @NotBlank String number,

        String complement,

        @NotBlank String neighborhood,

        @NotBlank String locality,

        @NotBlank String uf,
    
        @NotBlank String city) {

    public void updateAdress(DateAdress address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAdress'");
    }

}
