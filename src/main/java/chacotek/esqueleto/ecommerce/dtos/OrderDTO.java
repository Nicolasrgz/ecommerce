package chacotek.esqueleto.ecommerce.dtos;

import chacotek.esqueleto.ecommerce.models.Order;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class OrderDTO {
    private Long id;
    private String description, numberOrder, category;
    private LocalDate date;
    private Integer productQuantity;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.description = order.getDescription();
        this.numberOrder = order.getNumberOrder();
        this.category = order.getCategory();
        this.date = order.getDate();
        this.productQuantity = order.getProductQuantity();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }
}
