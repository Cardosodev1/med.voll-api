package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorSchedulingOtherConsultation implements IScheduling {

    @Autowired
    private ConsultationRepository repository;

    @Override
    public void validate(ConsultationDTO dto) {
        var doctorHasAnotherConsultationAtTheSameTime = repository.existsByDoctorIdAndDateConsultation(dto.idDoctor(), dto.dateConsultation());
        if (doctorHasAnotherConsultationAtTheSameTime) {
            throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
