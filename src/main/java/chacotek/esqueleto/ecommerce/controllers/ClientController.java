package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.ClientDTO;
import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import com.sun.mail.iap.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

        Client client = new Client(clientDTO.getName(), clientDTO.getDirection(), passwordEncoder.encode(clientDTO.getPassword()), clientDTO.getPhoneNumber(), clientDTO.getEmail());
        clientRepository.save(client);

        return new ResponseEntity<>("the user has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/client/current/edit")
    public ResponseEntity<Object>editClient(@RequestBody ClientDTO clientDTO, Authentication authentication){
        Client clientCurrent = clientRepository.findByEmail(authentication.name());

        if (clientCurrent == null){
            return new ResponseEntity<>("the user is null", HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getPhoneNumber().isBlank() && clientDTO.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>("The phone number field is incomplete", HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getDirection().isBlank() && clientDTO.getDirection().isEmpty()){
            return new ResponseEntity<>("The direction field is incomplete", HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getName().isBlank() && clientDTO.getName().isEmpty()){
            return new ResponseEntity<>("The name field is incomplete", HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getEmail().isBlank() && clientDTO.getEmail().isEmpty()){
            return new ResponseEntity<>("The email field is incomplete", HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getPassword().isBlank() && clientDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("The password field is incomplete", HttpStatus.BAD_REQUEST);
        }

        clientCurrent.setDirection(clientDTO.getDirection());
        clientCurrent.setName(clientDTO.getName());
        clientCurrent.setEmail(clientDTO.getEmail());
        clientCurrent.setPassword(clientDTO.getPassword());
        clientCurrent.setPhoneNumber(clientDTO.getPhoneNumber());

        try {
            clientRepository.save(clientCurrent);
            return  new ResponseEntity<>("the user has been edited successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("There was an error setting the changes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
