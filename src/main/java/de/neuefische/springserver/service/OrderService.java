package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Order;
import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.repository.OrderRepository;
import de.neuefische.springserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepo, ProductRepository productRepository) {
        this.orderRepo = orderRepo;
        this.productRepository = productRepository;
    }

    public Optional<Order> addOrder(Order order){
        for (Product product : order.getProducts()) {
            if (productRepository.getProductById(product.getId()).isEmpty()) {
                return Optional.empty();
            }
        }
        return Optional.of(orderRepo.addOrder(order));
    }

    public void removeOrderById(String id){
        orderRepo.removeOrderById(id);

    }

    public List<Order> getOrderList(){
        return orderRepo.getOrderList();
    }

    public Optional<Order> getOrderById(String id){
        return orderRepo.getOrderById(id);
    }
}
