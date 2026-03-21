package com.salesystem;


import com.salesystem.manager.FileOrderManager;
import com.salesystem.model.OrderResult;
import com.salesystem.parser.OrderParser;
import com.salesystem.service.FileOrderService;
import com.salesystem.service.OrderService;


public class Main {
    public static void main(String[] args) {
        String filePath;
        FileOrderService fileOrderService = new FileOrderService();
        OrderParser orderParser = new OrderParser();
        OrderService orderService = new OrderService();
        FileOrderManager fileOrderManager = new FileOrderManager(
                fileOrderService,
                orderParser,
                orderService);
        fileOrderManager.manage(
                "C:\\Users\\Андрей\\IdeaProjects\\sales_system\\InteractiveSalesSystem\\src\\main\\resources\\discount_day.txt",
                0.5, 0.05, 0.0,
                "C:\\Users\\Андрей\\IdeaProjects\\sales_system\\InteractiveSalesSystem\\src\\main\\resources\\orderResults.txt"
        );

    }
}
