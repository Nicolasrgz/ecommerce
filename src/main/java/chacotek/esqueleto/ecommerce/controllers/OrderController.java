package chacotek.esqueleto.ecommerce.controllers;

import chacotek.esqueleto.ecommerce.dtos.OrderDTO;
import chacotek.esqueleto.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<OrderDTO> getOrders(){
        return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}
