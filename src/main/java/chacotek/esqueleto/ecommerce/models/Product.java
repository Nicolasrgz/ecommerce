package chacotek.esqueleto.ecommerce.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, description, category, img;
    private Integer stock, price;
    private Boolean isActive;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<OrderProduct>orderProducts = new HashSet<>();

    public Product() {
    }

    public Product(String name, String description, String category, Integer stock, Integer price, String img, Boolean isActive) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.img = img;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        orderProduct.setProduct(this);
        orderProducts.add(orderProduct);
    }
}
