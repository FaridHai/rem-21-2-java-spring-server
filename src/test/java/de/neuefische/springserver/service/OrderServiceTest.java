package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Order;
import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


class OrderServiceTest {
    OrderRepository orderRepoMock = mock(OrderRepository.class);

    @Test
    void addOrder() {
        //GIVEN
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        when(orderRepoMock.addOrder(order1)).thenReturn(order1);
        OrderService orderService = new OrderService(orderRepoMock);

        //WHEN
         Order actual = orderService.addOrder(order1);

         //THEN
        assertThat(actual,equalTo(order1));
        verify(orderRepoMock).addOrder(order1);
    }

    @Test
    void removeOrderById() {
        //GIVEN
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        doNothing().when(orderRepoMock).removeOrderById("1");
        OrderService orderService = new OrderService(orderRepoMock);

        //WHEN
        orderService.removeOrderById("1");

        //THEN
        verify(orderRepoMock).removeOrderById("1");
    }

    @Test
    void getOrderList() {
        //GIVEN
        Order order1 = new Order("1", List.of(
                new Product("Cherrys","1"),
                new Product("Apple","2")
        ));
        Order order2 = new Order("2", List.of(
                new Product("Gum","4"),
                new Product("Sirup","6")
        ));

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        when(orderRepoMock.getOrderList()).thenReturn(orderList);
        OrderService orderService = new OrderService(orderRepoMock);

        //WHEN
        List<Order> actual = orderService.getOrderList();

        //THEN
        assertThat(actual, containsInAnyOrder(order1, order2));
        verify(orderRepoMock).getOrderList();
    }

    @Test
    void getOrderById() {
        //GIVEN
        Order order2 = new Order("2", List.of(
                new Product("Gum","4"),
                new Product("Sirup","6")
        ));

        when(orderRepoMock.getOrderById("2")).thenReturn(Optional.of(order2));
        OrderService orderService = new OrderService(orderRepoMock);

        //WHEN
        Optional<Order> actual = orderService.getOrderById("2");

        //THEN
        assertThat(actual, isPresentAndIs(order2));
        verify(orderRepoMock).getOrderById("2");

    }
}