package com.api.domain.patient;

import java.util.Objects;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing Patient business logic.
 * Handles CRUD operations and ensures data consistency before persistence.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Persists a new patient in the database.
     * 
     * @param patient The patient entity to be saved.
     * @return The saved patient with generated database ID.
     */
    @Transactional
    public Patient register(Patient patient) {
        Objects.requireNonNull(patient, "Patient cannot be null");
        return patientRepository.save(patient);
    }

    /**
     * Retrieves a patient by their ID.
     * 
     * @throws RuntimeException if the patient is not found.
     */
    public Patient list(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    /**
     * Returns a paginated list of all patients with 'ativo' status set to true.
     */
    public Page<Patient> findAllByAtivoTrue(Pageable pageable) {
        return patientRepository.findAllByAtivoTrue(pageable);
    }

    /**
     * Gets a JPA proxy reference for the patient.
     * Ideal for performance when establishing relationships without loading full
     * data.
     */
    public Patient getReferenceById(Long id) {
        return patientRepository.getReferenceById(id);
    }

    /**
     * Updates an existing patient's details.
     * It fetches the current state from DB and updates permitted fields.
     */
    @Transactional
    public Patient update(Patient patient) {
        Patient patientDB = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Manual mapping of updated fields
        patientDB.setName(patient.getName());
        patientDB.setEmail(patient.getEmail());
        patientDB.setPhone(patient.getPhone());
        patientDB.setAddress(patient.getAddress());

        return patientRepository.save(patientDB);
    }

    /**
     * Performs a logical deletion (Soft Delete) of the patient.
     * Instead of removing the row, it flips the 'ativo' flag to false.
     */
    @Transactional
    public void excluir(Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }
}