package com.salesystem.manager;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;
import com.salesystem.parser.OrderParser;
import com.salesystem.service.FileOrderService;
import com.salesystem.service.OrderService;

import java.util.List;

public class FileOrderManager {

    private final FileOrderService fileOrderService;
    private final OrderParser orderParser;
    private final OrderService orderService;

    public FileOrderManager(
            FileOrderService fileOrderService,
            OrderParser orderParser,
            OrderService orderService
    ) {
        this.fileOrderService = fileOrderService;
        this.orderParser = orderParser;
        this.orderService = orderService;
    }

    public void manage(String filePath, double baseDiscount, double stepDiscount, double minDiscount, String fileName, double price) {
        List<String> stringOrders = fileOrderService.read(filePath);
        List<Order> orders = orderParser.parse(stringOrders);
        List<OrderResult> orderResults = orderService.calculateOrderResults(orders, baseDiscount, stepDiscount, minDiscount, price);
        fileOrderService.save(orderResults, fileName);
    }
}