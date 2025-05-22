package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHoursValidator implements IValidator {

    @Override
    public void validate(ConsultationDTO dto) {
        var dateConsultation = dto.dateConsultation();

        var sunday = dateConsultation.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = dateConsultation.getHour() < 7;
        var afterTheClinicCloses = dateConsultation.getHour() > 18;
        if (sunday || beforeTheClinicOpens || afterTheClinicCloses) {
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
