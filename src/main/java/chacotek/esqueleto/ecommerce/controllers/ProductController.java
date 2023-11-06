package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.ProductDTO;
import chacotek.esqueleto.ecommerce.models.Product;
import chacotek.esqueleto.ecommerce.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductDTO>getProducts(){
        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/created/product")
    public ResponseEntity<Object>createdProduct(@RequestBody ProductDTO productDTO){
        if(productDTO.getName().isBlank() && productDTO.getName().isEmpty()){
            return new ResponseEntity<>("The name of product field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getCategory().isBlank() && productDTO.getCategory().isEmpty()){
            return new ResponseEntity<>("The category of product field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getDescription().isBlank() && productDTO.getDescription().isEmpty()){
            return new ResponseEntity<>("The description of product field is empty or incorrect", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getPrice() <= 0){
            return new ResponseEntity<>("The price of the product cannot be less than or equal to 0", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getPrice() == null){
            return new ResponseEntity<>("the product must contain a purchase price", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getStock() == null){
            return new ResponseEntity<>("stock cannot be null", HttpStatus.FORBIDDEN);
        }

        try {
            Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getCategory(), productDTO.getStock(), productDTO.getPrice(), productDTO.getImg(), productDTO.getActive());
            productRepository.save(product);
            return new ResponseEntity<>("the product has been created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("the creation of the product has not been possible", HttpStatus.CONFLICT);
        }
    }
}