package med.voll.api.domain.repository;

import med.voll.api.domain.entity.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    boolean existsByDoctorIdAndDateConsultation(Long idDoctor, LocalDateTime dateConsultation);

    boolean existsByPatientIdAndDateConsultationBetween(Long idPatient, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
}
