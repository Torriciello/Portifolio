package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.domain.doctor.Doctor;
import com.api.domain.doctor.DoctorDetails;
import com.api.domain.doctor.DoctorService;
import com.api.domain.doctor.RegisterDoctor;
import com.api.domain.doctor.UpdateDoctor;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("/doctor")
@RestController
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DoctorDetails> register(@RequestBody @Valid RegisterDoctor data) {
        var doctor = new Doctor(data);
        doctorService.register(doctor);
        return ResponseEntity.ok(new DoctorDetails(doctor));
    }

    @GetMapping
    public Page<DoctorDetails> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pageable) {
        return doctorService.findAllByAtivoTrue(pageable).map(DoctorDetails::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctor updateDoctor) {
        var doctor = doctorService.getReferenceById(updateDoctor.id());
        doctor.update(updateDoctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = doctorService.getReferenceById(id);
        doctor.delete();
    }

}