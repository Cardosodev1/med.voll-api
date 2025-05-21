package med.voll.api.domain.repository;

import med.voll.api.domain.entity.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
