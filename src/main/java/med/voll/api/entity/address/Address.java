package med.voll.api.entity.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public void update(AddressDTO addressDTO) {
        if (addressDTO.street() != null) {
            this.street = addressDTO.street();
        }
        if (addressDTO.neighborhood() != null) {
            this.neighborhood = addressDTO.neighborhood();
        }
        if (addressDTO.zipCode() != null) {
            this.zipCode = addressDTO.zipCode();
        }
        if (addressDTO.city() != null) {
            this.city = addressDTO.city();
        }
        if (addressDTO.state() != null) {
            this.state = addressDTO.state();
        }
        if (addressDTO.number() != null) {
            this.number = addressDTO.number();
        }
        if (addressDTO.complement() != null) {
            this.complement = addressDTO.complement();
        }
    }

}