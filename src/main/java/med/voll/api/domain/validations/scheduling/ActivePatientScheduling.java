package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientScheduling implements IScheduling {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(ConsultationDTO dto) {
        var patientIsActive = repository.findActiveById(dto.idPatient());
        if (!patientIsActive) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}
