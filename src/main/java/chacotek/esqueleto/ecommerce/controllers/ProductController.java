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



}