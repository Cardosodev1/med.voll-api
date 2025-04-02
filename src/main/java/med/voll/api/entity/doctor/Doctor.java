package med.voll.api.entity.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.entity.address.Address;
import med.voll.api.dto.DoctorDTO;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorDTO doctorDTO) {
        this.name = doctorDTO.name();
        this.email = doctorDTO.email();
        this.phone = doctorDTO.phone();
        this.crm = doctorDTO.crm();
        this.specialty = doctorDTO.specialty();
        this.address = new Address(doctorDTO.address());
    }

}
