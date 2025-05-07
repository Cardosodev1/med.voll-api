package med.voll.api.domain.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.address.AddressDTO;

public record DoctorUpdateDTO(

        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO addressDTO) {
}
