package med.voll.api.domain.dto.doctor;

import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.entity.doctor.Specialty;

public record DoctorListDTO(Long id,
                            String name,
                            String email,
                            String crm,
                            Specialty specialty) {

    public DoctorListDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
