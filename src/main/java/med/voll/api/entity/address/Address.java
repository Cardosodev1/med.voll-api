package med.voll.api.entity.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.street();
        this.neighborhood = addressDTO.neighborhood();
        this.zipCode = addressDTO.zipCode();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.number = addressDTO.number();
        this.complement = addressDTO.complement();
    }

}