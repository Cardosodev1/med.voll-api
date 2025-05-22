package med.voll.api.domain.service;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.dto.consultation.ConsultationDetailsDTO;
import med.voll.api.domain.entity.consultation.Consultation;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.repository.ConsultationRepository;
import med.voll.api.domain.repository.DoctorRepository;
import med.voll.api.domain.repository.PatientRepository;
import med.voll.api.domain.validations.scheduling.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<IValidator> validators;

    public ConsultationDetailsDTO schedule(ConsultationDTO dto) {
        if (!patientRepository.existsById(dto.idPatient())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }

        if (dto.idDoctor() != null && !doctorRepository.existsById(dto.idDoctor())) {
            throw new ValidationException("Id do médico informado não existe!");
        }

        validators.forEach(v -> v.validate(dto));

        var patient = patientRepository.getReferenceById(dto.idPatient());
        var doctor = chooseDoctor(dto);
        if (doctor == null) {
            throw new ValidationException("Não existe médico disponível nessa data!");
        }

        var consultation = new Consultation(null, doctor, patient, dto.dateConsultation());
        consultationRepository.save(consultation);

        return new ConsultationDetailsDTO(consultation);
    }

    private Doctor chooseDoctor(ConsultationDTO dto) {
        if(dto.idDoctor() != null) {
            return doctorRepository.getReferenceById(dto.idDoctor());
        }

        if (dto.specialty() == null) {
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return doctorRepository.chooseRandomDoctor(dto.specialty(), dto.dateConsultation());
    }

}
