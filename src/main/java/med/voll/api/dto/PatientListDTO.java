package med.voll.api.dto;

import med.voll.api.entity.patient.Patient;

public record PatientListDTO(Long id,
                             String name,
                             String email,
                             String cpf) {

    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
