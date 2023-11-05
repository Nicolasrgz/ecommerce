package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.ProductDTO;
import chacotek.esqueleto.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
