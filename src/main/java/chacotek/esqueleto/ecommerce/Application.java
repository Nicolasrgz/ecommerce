package chacotek.esqueleto.ecommerce;

import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.models.Order;
import chacotek.esqueleto.ecommerce.models.OrderProduct;
import chacotek.esqueleto.ecommerce.models.Product;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import chacotek.esqueleto.ecommerce.repositories.OrderProductRepository;
import chacotek.esqueleto.ecommerce.repositories.OrderRepository;
import chacotek.esqueleto.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository){
		return (args) ->{
			Client client = new Client("nombre", "direccion", passwordEncoder.encode("12345678"), "1234567890", "nombre@correo.com" );
			clientRepository.save(client);

			Order order = new Order("111","111","111", LocalDate.now(),10);
			client.addOrder(order);
			orderRepository.save(order);

			Product product = new Product("nombre", "descripcion", "pepe", 111, 1);
			productRepository.save(product);

			OrderProduct orderProduct = new OrderProduct(1000, 100, 10);
			order.addOrderProduct(orderProduct);
			product.addOrderProduct(orderProduct);
			orderProductRepository.save(orderProduct);

		};
	}
}