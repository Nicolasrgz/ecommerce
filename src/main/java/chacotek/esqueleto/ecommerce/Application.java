package chacotek.esqueleto.ecommerce;

import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.models.Order;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import chacotek.esqueleto.ecommerce.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, OrderRepository orderRepository){
		return (args) ->{
			Client client = new Client("nombre", "direccion", "12345678", "1234567890", "nombre@correo.com" );
			clientRepository.save(client);

			Order order = new Order("111","111","111", LocalDate.now(),10);
			client.addOrder(order);
			orderRepository.save(order);

		};
	}
}