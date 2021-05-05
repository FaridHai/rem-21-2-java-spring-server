package de.neuefische.springserver.repository;

import de.neuefische.springserver.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final Map<String, Order> orderRepo = new HashMap<>();

    public List<Order> getOrderList(){
        return List.copyOf(orderRepo.values());
    }

    public Optional<Order> getOrderById(String id){
        return Optional.ofNullable(orderRepo.get(id));
    }

    public Order addOrder(Order order){
        orderRepo.put(order.getId(),order);
        return order;
    }


    public void removeOrderById(String id){
        orderRepo.remove(id);

    }
}
