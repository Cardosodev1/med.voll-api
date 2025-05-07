package med.voll.api.domain.dto.patient;

import med.voll.api.domain.entity.patient.Patient;

public record PatientListDTO(Long id,
                             String name,
                             String email,
                             String cpf) {

    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
