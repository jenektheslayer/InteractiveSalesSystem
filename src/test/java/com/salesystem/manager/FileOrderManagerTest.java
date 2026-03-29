package com.salesystem.manager;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;
import com.salesystem.parser.OrderParser;
import com.salesystem.service.FileOrderService;
import com.salesystem.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileOrderManagerTest {

    @Mock
    private FileOrderService fileOrderService;

    @Mock
    private OrderParser orderParser;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private FileOrderManager fileOrderManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldManageFileSuccessfully() {
        String filePath = "orders.txt";
        String fileName = "result.txt";
        double baseDiscount = 0.1;
        double stepDiscount = 0.05;
        double minDiscount = 0.02;
        double price = 100.0;

        List<String> rawLines = List.of("order1", "order2");
        List<Order> orders = List.of(new Order(LocalDateTime.now(),"TestCompany", 10.0));
        List<OrderResult> orderResults = List.of(new OrderResult("TestCompany", 100.0));

        when(fileOrderService.read(filePath)).thenReturn(rawLines);
        when(orderParser.parse(rawLines)).thenReturn(orders);
        when(orderService.calculateOrderResults(orders, baseDiscount, stepDiscount, minDiscount, price)).thenReturn(orderResults);

        fileOrderManager.manage(filePath, baseDiscount, stepDiscount, minDiscount, fileName, price);

        verify(fileOrderService).read(filePath);
        verify(orderParser).parse(rawLines);
        verify(orderService).calculateOrderResults(orders, baseDiscount, stepDiscount, minDiscount, price);
        verify(fileOrderService).save(orderResults, fileName);
    }

}