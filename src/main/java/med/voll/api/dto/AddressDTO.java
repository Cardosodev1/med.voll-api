package med.voll.api.dto;

public record AddressDTO(String street,
                         String neighborhood,
                         String zipCode,
                         String city,
                         String state,
                         String number,
                         String complement) {
}
