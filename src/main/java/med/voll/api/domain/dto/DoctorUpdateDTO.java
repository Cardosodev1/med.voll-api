package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO(

        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO addressDTO) {
}
