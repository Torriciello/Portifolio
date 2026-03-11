package com.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String cep;
    private String publicPlace;
    private String number;
    private String complement;
    private String neighborhood;
    private String locality;
    private String uf;
    private String city;

    public Address(DateAdress dateAdress) {
        this.publicPlace = dateAdress.publicPlace();
        this.neighborhood = dateAdress.neighborhood();
        this.cep = dateAdress.cep();
        this.uf = dateAdress.uf();
        this.locality = dateAdress.locality();
        this.city = dateAdress.city();
        this.number = dateAdress.number();
        this.complement = dateAdress.complement();
    }

    public void updateAdress(DateAdress address) {

        if (address.publicPlace() != null) {
            this.publicPlace = address.publicPlace();
        }

        if (address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }

        if (address.cep() != null) {
            this.cep = address.cep();
        }

        if (address.uf() != null) {
            this.uf = address.uf();
        }

        if (address.locality() != null) {
            this.locality = address.locality();
        }

        if (address.city() != null) {
            this.city = address.city();
        }

        if (address.number() != null) {
            this.number = address.number();
        }

        if (address.complement() != null) {
            this.complement = address.complement();
        }
    }
}