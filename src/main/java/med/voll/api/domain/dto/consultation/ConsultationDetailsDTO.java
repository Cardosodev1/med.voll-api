package med.voll.api.domain.dto.consultation;

import java.time.LocalDateTime;

public record ConsultationDetailsDTO(Long id,
                                     Long idDoctor,
                                     Long idPatient,
                                     LocalDateTime dateConsultation) {
}
