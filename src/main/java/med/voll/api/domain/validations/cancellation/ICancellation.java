package med.voll.api.domain.validations.cancellation;

import med.voll.api.domain.dto.consultation.ConsultationCancellationDTO;

public interface ICancellation {

    void validate(ConsultationCancellationDTO dto);

}
