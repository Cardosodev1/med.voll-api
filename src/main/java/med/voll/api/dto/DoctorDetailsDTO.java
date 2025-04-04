package med.voll.api.dto;

import med.voll.api.entity.address.Address;
import med.voll.api.entity.doctor.Doctor;
import med.voll.api.entity.doctor.Specialty;

public record DoctorDetailsDTO(Long id,
                               String name,
                               String email,
                               String phone,
                               String crm,
                               Specialty specialty,
                               Address address) {

    public DoctorDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }

}
