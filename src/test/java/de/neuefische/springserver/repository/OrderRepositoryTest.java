package de.neuefische.springserver.repository;

import de.neuefische.springserver.model.Order;
import de.neuefische.springserver.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;

class OrderRepositoryTest {
    @Test
    void addOrder() {
        //GIVEN
        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));

        //WHEN
        orderRepository.addOrder(order);

        //THEN
        assertThat(orderRepository.getOrderById("1"),isPresentAndIs(order));
    }



    @Test
    void getOrderList() {
        //GIVEN
        OrderRepository orderRepository = new OrderRepository();
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        Order order2 = new Order("2", List.of(
                new Product("Banana","3"),
                new Product("Cake","4")
        ));
        Order order3 = new Order("3", List.of(
                new Product("Blueberries","5")
        ));

        orderRepository.addOrder(order1);
        orderRepository.addOrder(order2);
        orderRepository.addOrder(order3);

        //WHEN
        List<Order> actual = orderRepository.getOrderList();

        //THEN
        assertThat(actual, containsInAnyOrder(order1,order2,order3));
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderRepository orderRepository = new OrderRepository();
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        Order order2 = new Order("2", List.of(
                new Product("Banana","3"),
                new Product("Cake","4")
        ));
        Order order3 = new Order("3", List.of(
                new Product("Blueberries","5")
        ));

        orderRepository.addOrder(order1);
        orderRepository.addOrder(order2);
        orderRepository.addOrder(order3);

        //WHEN
        Optional<Order> actual = orderRepository.getOrderById("2");
        Optional<Order> actual2 = orderRepository.getOrderById("3");

        //THEN
        assertThat(actual, isPresentAndIs(new Order("2", List.of(
                new Product("Banana","3"),
                new Product("Cake","4")
        ))));

        assertThat(actual2, isPresentAndIs(order3));
    }



    @Test
    void removeOrderById() {
        //GIVEN
        OrderRepository orderRepository = new OrderRepository();
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        Order order2 = new Order("2", List.of(
                new Product("Banana","3"),
                new Product("Cake","4")
        ));
        Order order3 = new Order("3", List.of(
                new Product("Blueberries","5")
        ));

        orderRepository.addOrder(order1);
        orderRepository.addOrder(order2);
        orderRepository.addOrder(order3);

        //WHEN
        orderRepository.removeOrderById("2");

        //THEN
        assertThat(orderRepository.getOrderList(), containsInAnyOrder(order1,order3));
    }
}