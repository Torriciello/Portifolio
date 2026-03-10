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
}