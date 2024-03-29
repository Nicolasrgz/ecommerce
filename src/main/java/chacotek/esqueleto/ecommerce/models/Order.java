package chacotek.esqueleto.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String description, numberOrder, category;
    private LocalDate date;
    private Integer productQuantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order() {
    }

    public Order(String description, String numberOrder, String category, LocalDate date, Integer productQuantity) {
        this.description = description;
        this.numberOrder = numberOrder;
        this.category = category;
        this.date = date;
        this.productQuantity = productQuantity;
    }

    public Long getId() {
        return id;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        orderProduct.setOrder(this);
        orderProducts.add(orderProduct);
    }
}
