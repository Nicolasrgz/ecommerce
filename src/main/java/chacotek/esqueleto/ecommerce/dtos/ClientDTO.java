package chacotek.esqueleto.ecommerce.dtos;

import chacotek.esqueleto.ecommerce.models.Client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direction;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String phoneNumber;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
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
