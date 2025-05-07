package med.voll.api.domain.entity.patient;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dto.patient.PatientDTO;
import med.voll.api.domain.dto.patient.PatientUpdateDTO;
import med.voll.api.domain.entity.address.Address;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientDTO patientDTO) {
        this.name = patientDTO.name();
        this.email = patientDTO.email();
        this.phone = patientDTO.phone();
        this.cpf = patientDTO.cpf();
        this.address = new Address(patientDTO.address());
        this.active = true;
    }

    public void update(@Valid PatientUpdateDTO patientUpdateDTO) {
        if (patientUpdateDTO.name() != null) {
            this.name = patientUpdateDTO.name();
        }
        if (patientUpdateDTO.phone() != null) {
            this.phone = patientUpdateDTO.phone();
        }
        if (patientUpdateDTO.addressDTO() != null) {
            this.address.update(patientUpdateDTO.addressDTO());
        }
    }

    public void delete() {
        this.active = false;
    }

}
