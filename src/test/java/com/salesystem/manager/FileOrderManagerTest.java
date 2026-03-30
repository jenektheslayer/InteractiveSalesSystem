package com.salesystem.manager;

import com.salesystem.exception.IORuntimeException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldNotContinueWhenReadThrowsException() {
        String filePath = "orders.txt";
        String fileName = "result.txt";
        double baseDiscount = 0.1;
        double stepDiscount = 0.05;
        double minDiscount = 0.02;
        double price = 100.0;

        when(fileOrderService.read(filePath)).thenThrow(new RuntimeException("Read failed"));

        assertThrows(RuntimeException.class, () ->
                fileOrderManager.manage(filePath, baseDiscount, stepDiscount, minDiscount, fileName, price)
        );
        verify(fileOrderService).read(filePath);
        verify(orderParser, never()).parse(anyList());
        verify(orderService, never()).calculateOrderResults(anyList(), anyDouble(), anyDouble(), anyDouble(), anyDouble());
        verify(fileOrderService, never()).save(anyList(), anyString());
    }

    @Test
    void shouldNotSaveWhenParseThrowsException() {
        String filePath = "orders.txt";
        String fileName = "result.txt";
        double baseDiscount = 0.1;
        double stepDiscount = 0.05;
        double minDiscount = 0.02;
        double price = 100.0;

        List<String> rawsLines = List.of("order1","order2");

        when(fileOrderService.read(filePath)).thenReturn(rawsLines);
        when(orderParser.parse(rawsLines)).thenThrow(new RuntimeException("Parse failed"));

        assertThrows(RuntimeException.class, () ->
                fileOrderManager.manage(filePath, baseDiscount, stepDiscount, minDiscount, fileName, price)
        );

        verify(fileOrderService).read(filePath);
        verify(orderParser).parse(rawsLines);
        verify(orderService, never()).calculateOrderResults(anyList(), anyDouble(), anyDouble(), anyDouble(), anyDouble()
        );

    }
}