package com.api.domain.patient;

import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient register(Patient patient) {
        Objects.requireNonNull(patient, "Patient cannot be null");
        return patientRepository.save(patient);
    }

    public Patient list(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Page<Patient> findAllByAtivoTrue(Pageable pageable) {
        return patientRepository.findAllByAtivoTrue(pageable);
    }

    public Patient getReferenceById(Long id) {
        return patientRepository.getReferenceById(id);
    }

    public Patient update(Patient patient) {

        Patient patientDB = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        patientDB.setName(patient.getName());
        patientDB.setEmail(patient.getEmail());
        patientDB.setPhone(patient.getPhone());
        patientDB.setAddress(patient.getAddress());

        return patientRepository.save(patientDB);
    }

    public void excluir(Long id) {
    var patient = patientRepository.getReferenceById(id);
    patient.excluir();
}
}