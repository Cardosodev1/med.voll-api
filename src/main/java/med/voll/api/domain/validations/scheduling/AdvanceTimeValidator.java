package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements IValidator {

    @Override
    public void validate(ConsultationDTO dto) {
        var dateConsultation = dto.dateConsultation();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateConsultation).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
