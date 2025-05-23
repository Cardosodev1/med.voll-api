package med.voll.api.domain.validations.scheduling;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientSchedulingWithoutAnotherConsultationThatDay implements IScheduling {

    @Autowired
    private ConsultationRepository repository;

    @Override
    public void validate(ConsultationDTO dto) {
        var firstAppointment = dto.dateConsultation().withHour(7);
        var lastAppointment = dto.dateConsultation().withHour(18);
        var patientHasAnotherConsultationThatDay = repository.existsByPatientIdAndDateConsultationBetween(dto.idPatient(), firstAppointment, lastAppointment);
        if (patientHasAnotherConsultationThatDay) {
            throw new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
