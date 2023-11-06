package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.ProductDTO;
import chacotek.esqueleto.ecommerce.models.Client;
import chacotek.esqueleto.ecommerce.models.Product;
import chacotek.esqueleto.ecommerce.repositories.ClientRepository;
import chacotek.esqueleto.ecommerce.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;

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

    @PatchMapping("/product/{id}/deactivate")
    public ResponseEntity<Object> deactivateProduct(@PathVariable Long id, Authentication authentication) {
        Client admin = clientRepository.findByEmail(authentication.name());

        if (admin == null) {
            return new ResponseEntity<>("only an administrator can use this servlet", HttpStatus.BAD_REQUEST);
        }

        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/product/{id}/activate")
    public ResponseEntity<Object> activateProduct(@PathVariable Long id, Authentication authentication) {
        Client admin = clientRepository.findByEmail(authentication.name());

        if (admin == null) {
            return new ResponseEntity<>("only an administrator can use this servlet", HttpStatus.BAD_REQUEST);
        }

        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(true);
                    productRepository.save(product);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/product/{id}/modification")
    public ResponseEntity<Object> modificationProducts(@PathVariable long id, @RequestBody ProductDTO modification, Authentication authentication){
        Client admin = clientRepository.findByEmail(authentication.name());

        if (admin == null) {
            return new ResponseEntity<>("only an administrator can use this servlet", HttpStatus.BAD_REQUEST);
        }

        Optional<Product> productoOptional = productRepository.findById(id);
        if (productoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product producto = productoOptional.get();

        if (modification.getName() == null || modification.getName().isBlank()) {
            return new ResponseEntity<>("Product name is null or empty", HttpStatus.BAD_REQUEST);
        }
        if (modification.getStock() <= 0) {
            return new ResponseEntity<>("Assign a stock quantity", HttpStatus.BAD_REQUEST);
        }
        if (modification.getDescription() == null || modification.getDescription().isBlank()) {
            return new ResponseEntity<>("Define a product description", HttpStatus.BAD_REQUEST);
        }
        if (modification.getPrice() <= 0) {
            return new ResponseEntity<>("Define a price for the product", HttpStatus.BAD_REQUEST);
        }
        if (modification.getCategory() == null || modification.getCategory().isBlank()) {
            return new ResponseEntity<>("Define a brand for the product", HttpStatus.BAD_REQUEST);
        }

        try {
            producto.setName(modification.getName());
            producto.setCategory(modification.getCategory());
            producto.setDescription(modification.getDescription());
            producto.setStock(modification.getStock());
            producto.setPrice(modification.getPrice());

            productRepository.save(producto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Product could not be modified", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}