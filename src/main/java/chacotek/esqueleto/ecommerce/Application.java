package chacotek.esqueleto.ecommerce;

import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository){
		return (args) ->{
			Client client = new Client("nombre", "direccion", "12345678", "1234567890", "nombre@correo.com" );
			clientRepository.save(client);
		};
	}
}