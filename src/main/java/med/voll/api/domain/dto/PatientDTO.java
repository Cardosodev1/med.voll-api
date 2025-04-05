package med.voll.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientDTO(

        @NotNull
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull
        @Valid
        AddressDTO address) {
}
