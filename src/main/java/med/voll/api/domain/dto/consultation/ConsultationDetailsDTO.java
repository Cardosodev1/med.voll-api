package med.voll.api.domain.dto.consultation;

import med.voll.api.domain.entity.consultation.Consultation;

import java.time.LocalDateTime;

public record ConsultationDetailsDTO(Long id,
                                     Long idDoctor,
                                     Long idPatient,
                                     LocalDateTime dateConsultation) {

    public ConsultationDetailsDTO(Consultation consultation) {
        this(consultation.getId(), consultation.getDoctor().getId(), consultation.getPatient().getId(), consultation.getDateConsultation());
    }

}
