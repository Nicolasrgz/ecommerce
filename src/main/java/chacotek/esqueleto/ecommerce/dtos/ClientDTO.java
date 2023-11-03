package chacotek.esqueleto.ecommerce.dtos;

import chacotek.esqueleto.ecommerce.models.Client;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

public class ClientDTO {

    private Long id;
    private String name, direction, password, phoneNumber;
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.direction = client.getDirection();
        this.password = client.getPassword();
        this.phoneNumber = client.getPhoneNumber();
        this.email = client.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
