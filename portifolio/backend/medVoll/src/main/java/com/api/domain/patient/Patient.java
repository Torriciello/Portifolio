package com.api.domain.patient;

import com.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "patients")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean ativo;

    public Patient(RegisterPatient registerPatient) {
        this.ativo = true;
        this.name = registerPatient.name();
        this.email = registerPatient.email();
        this.phone = registerPatient.telefone();
        this.cpf = registerPatient.cpf();
        this.address = new Address();
    }

    public void update(UpdatePatient updatePatient) {
        if (updatePatient.name() != null) {
            this.name = updatePatient.name();
        }
        if (updatePatient.telefone() != null) {
            this.phone = updatePatient.telefone();
        }
        if (updatePatient.address() != null) {
            this.address.updateAdress(updatePatient.address());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
