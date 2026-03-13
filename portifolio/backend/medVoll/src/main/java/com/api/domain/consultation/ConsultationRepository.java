package com.api.domain.consultation;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstSchedule, LocalDateTime lastTime);

    boolean existsByDoctorIdAndDateAndReasonCancellationIsNull(Long idDoctor, LocalDateTime date);

    Page<Consultation> findAllByDoctorIdAndReasonCancellationIsNull(Long doctorId, Pageable pageable);
}