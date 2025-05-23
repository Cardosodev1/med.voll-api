package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.dto.consultation.ConsultationDTO;

public interface IScheduling {

    void validate(ConsultationDTO dto);

}
