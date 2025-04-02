package med.voll.api.dto;

import med.voll.api.entity.doctor.Doctor;
import med.voll.api.entity.doctor.Specialty;

public record DoctorListDTO(String name,
                            String email,
                            String crm,
                            Specialty specialty) {

    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
