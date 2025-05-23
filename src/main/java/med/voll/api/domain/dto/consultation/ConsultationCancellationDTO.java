package med.voll.api.domain.dto.consultation;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.validations.cancellation.CancellationReason;

public record ConsultationCancellationDTO(
        @NotNull
        Long idConsultation,

        @NotNull
        CancellationReason reason) {
}
