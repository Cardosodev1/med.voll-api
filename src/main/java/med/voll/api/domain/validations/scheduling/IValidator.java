package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.dto.consultation.ConsultationDTO;

public interface IValidator {

    void validate(ConsultationDTO dto);

}
