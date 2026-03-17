package com.salesystem.manager;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;
import com.salesystem.parser.OrderParser;
import com.salesystem.service.FileOrderService;
import com.salesystem.service.OrderService;

import java.util.List;

public class FileOrderManager {
    public void manage(String filePath, double baseDiscount, double stepDiscount, double minDiscount) {
        FileOrderService fileOrderService = new FileOrderService();
        List<String> stringOrders = fileOrderService.read(filePath);
        OrderParser orderParser = new OrderParser();
        List<Order> orders = orderParser.parse(stringOrders);
        OrderService orderService = new OrderService();
        List<OrderResult> orderResults = orderService.calculateOrderResults(orders,baseDiscount,stepDiscount,minDiscount);
//        for (OrderResult orderResult : orderResults) {
//            System.out.println(orderResult.getCompanyName() + " - " + orderResult.getTotalPrice());
//        }
        fileOrderService.save(orderResults,"orderResults");




    }
}
