package chacotek.esqueleto.ecommerce.repositories;

import chacotek.esqueleto.ecommerce.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
