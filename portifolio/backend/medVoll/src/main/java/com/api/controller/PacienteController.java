package com.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.domain.patient.Patient;
import com.api.domain.patient.PatientDetails;
import com.api.domain.patient.PatientService;
import com.api.domain.patient.UpdatePatient;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("/patients")
@RestController
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<PatientDetails> register(@RequestBody @Valid Patient patient) {
        Patient newPatient = patientService.register(patient);
        return ResponseEntity.ok(new PatientDetails(newPatient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetails> list(@PathVariable Long id) {
        Patient patient = patientService.list(id);
        return ResponseEntity.ok(new PatientDetails(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDetails>> listar(
            @PageableDefault(size = 10, sort = { "name" }) Pageable pageable) {
        var page = patientService.findAllByAtivoTrue(pageable)
                .map(PatientDetails::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetails> update(@RequestBody @Valid UpdatePatient updatePatient) {
        var patient = patientService.getReferenceById(updatePatient.id());
        patient.update(updatePatient);
        return ResponseEntity.ok(new PatientDetails(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var patient = patientService.getReferenceById(id);
        patient.excluir();
        return ResponseEntity.noContent().build();
    }

}