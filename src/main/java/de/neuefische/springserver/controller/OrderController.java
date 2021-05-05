package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Order;
import de.neuefische.springserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable String id){
        Optional<Order> optionalOrder = orderService.getOrderById(id);

        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

    @PutMapping("{id}")
    public Order addOrder(@PathVariable String id, @Valid @RequestBody Order order) {
        if (!id.equals(order.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id not valid");
        }
        Optional<Order> optionalOrder = orderService.addOrder(order);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Any product not available");
    }

    @DeleteMapping("{id}")
    public void removeOrder(@PathVariable String id) {
        orderService.removeOrderById(id);
    }
}
