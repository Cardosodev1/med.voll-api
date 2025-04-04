package med.voll.api.dto;

import med.voll.api.entity.address.Address;
import med.voll.api.entity.patient.Patient;

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
