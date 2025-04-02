package med.voll.api.dto;

import med.voll.api.entity.doctor.Specialty;

public record DoctorDTO(String name,
                        String email,
                        String crm,
                        Specialty specialty,
                        AddressDTO address) {
}
