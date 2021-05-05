package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Order;
import de.neuefische.springserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order addOrder(Order order){
        return orderRepo.addOrder(order);
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
