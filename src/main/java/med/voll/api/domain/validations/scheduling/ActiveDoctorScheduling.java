package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorScheduling implements IScheduling {

    @Autowired
    private DoctorRepository repository;

    @Override
    public void validate(ConsultationDTO dto) {
        if (dto.idDoctor() == null) {
            return;
        }

        var doctorIsActive = repository.findActiveById(dto.idDoctor());
        if (!doctorIsActive) {
            throw new ValidationException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
