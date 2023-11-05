package chacotek.esqueleto.ecommerce.dtos;

import chacotek.esqueleto.ecommerce.models.Product;

public class ProductDTO {
    private Long id;
    private String name, description, category;
    private Integer stock, price;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.stock = product.getStock();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getPrice() {
        return price;
    }
}
