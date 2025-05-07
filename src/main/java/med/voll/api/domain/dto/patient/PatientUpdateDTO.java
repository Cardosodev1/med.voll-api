package med.voll.api.domain.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.address.AddressDTO;

public record PatientUpdateDTO(

        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO addressDTO) {
}
