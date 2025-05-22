package med.voll.api.domain.service;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.entity.consultation.Consultation;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.repository.ConsultationRepository;
import med.voll.api.domain.repository.DoctorRepository;
import med.voll.api.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void schedule(ConsultationDTO dto) {
        if (!patientRepository.existsById(dto.idPatient())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }

        if (dto.idDoctor() != null && !doctorRepository.existsById(dto.idDoctor())) {
            throw new ValidationException("Id do médico informado não existe!");
        }

        var patient = patientRepository.getReferenceById(dto.idPatient());
        var doctor = chooseDoctor(dto);
        var consultation = new Consultation(null, doctor, patient, dto.dateConsultation());
        consultationRepository.save(consultation);
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
