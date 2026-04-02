package com.salesystem.service;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderServiceTest {

    private OrderService orderService = new OrderService();

    @Test
    void twoOrders() {

        List<Order> orders = new ArrayList<>(List.of(
                new Order(LocalDateTime.now(), "Company 1", 100.0),
                new Order(LocalDateTime.now(), "Company 2", 300.0),
                new Order(LocalDateTime.now(), "Company 1", 200.0)
        ));

        List<OrderResult> orderResults = orderService.calculateOrderResults(
                orders, 0.1, 0.02, 0.0, 10.0
        );

        assertEquals(2, orderResults.size());

        assertEquals("Company 1", orderResults.get(0).getCompanyName());

        assertEquals(2740, orderResults.get(0).getTotalPrice());
    }

    @Test
    void emptyList() {

        List<Order> orders = List.of();

        List<OrderResult> orderResults = orderService.calculateOrderResults(
                orders, 0.1, 0.05, 0.02, 10.0
        );
        assertTrue(orderResults.isEmpty());
    }

    @Test
    void minDiscountTest() {
        List<Order> orders = List.of(
                new Order(LocalDateTime.now(), "Company 1", 1.0),
                new Order(LocalDateTime.now(), "Company 1", 2.0),
                new Order(LocalDateTime.now(), "Company 1", 3.0),
                new Order(LocalDateTime.now(), "Company 1", 4.0)
        );

        List<OrderResult> orderResults = orderService.calculateOrderResults(orders, 0.1, 0.05, 0.02, 100.0
        );

        assertEquals(966, orderResults.get(0).getTotalPrice());
    }
}
