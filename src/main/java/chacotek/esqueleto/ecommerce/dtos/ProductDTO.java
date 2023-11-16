package chacotek.esqueleto.ecommerce.dtos;

import chacotek.esqueleto.ecommerce.models.Product;

public class ProductDTO {
    private Long id;
    private String name, description, category, img;
    private Integer stock, price;
    private Boolean isActive;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.img = product.getImg();
        this.isActive = product.getActive();
    }

    public Long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public Boolean getActive() {
        return isActive;
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
