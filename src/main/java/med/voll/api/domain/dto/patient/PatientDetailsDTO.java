package med.voll.api.domain.dto.patient;

import med.voll.api.domain.entity.address.Address;
import med.voll.api.domain.entity.patient.Patient;

public record PatientDetailsDTO(Long id,
                                String name,
                                String email,
                                String phone,
                                String cpf,
                                Address address) {

    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }

}
