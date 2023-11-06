package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.ClientDTO;
import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<ClientDTO>getClients(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/created/client")
    public ResponseEntity<Object>createdClient(@RequestBody ClientDTO clientDTO){
        if (clientDTO.getEmail().isBlank()){
            return new ResponseEntity<>("The email field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getPassword().isBlank()){
            return new ResponseEntity<>("The password field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getName().isBlank()){
            return new ResponseEntity<>("The name field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getDirection().isBlank()){
            return new ResponseEntity<>("The direction field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getPhoneNumber().isBlank() && clientDTO.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>("The phone number field is empty or incorrect", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(clientDTO.getName(), clientDTO.getDirection(), clientDTO.getPassword(), clientDTO.getPhoneNumber(), clientDTO.getEmail());
        clientRepository.save(client);

        return new ResponseEntity<>("the user has been created successfully", HttpStatus.CREATED);
    }
}
