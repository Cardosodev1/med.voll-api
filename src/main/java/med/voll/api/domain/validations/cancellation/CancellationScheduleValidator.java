package med.voll.api.domain.validations.cancellation;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationCancellationDTO;
import med.voll.api.domain.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancellationScheduleValidator implements ICancellation {

    @Autowired
    private ConsultationRepository repository;

    @Override
    public void validate(ConsultationCancellationDTO dto) {
        var consultation = repository.getReferenceById(dto.idConsultation());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, consultation.getDateConsultation()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }

}
