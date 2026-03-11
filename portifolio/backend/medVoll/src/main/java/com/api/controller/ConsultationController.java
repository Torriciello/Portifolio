package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.domain.consultation.AppointmentSchedule;
import com.api.domain.consultation.DataDetailsQuery;
import com.api.domain.consultation.DetailsConsultation;
import com.api.domain.consultation.validations.cancellation.CancellationDataConsultation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("consultation")
@RestController
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsQuery> schedule(@RequestBody @Valid DetailsConsultation data) {
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancellationDataConsultation data) {
        schedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
