package med.voll.api.domain.dto.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entity.doctor.Specialty;

import java.time.LocalDateTime;

public record ConsultationDTO(

        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime dateConsultation,

        Specialty specialty) {
}
