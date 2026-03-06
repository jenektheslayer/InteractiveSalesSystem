package com.salesystem.service;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;

import java.util.*;

public class OrderService {
    private static final double baseDiscount = 0.5;
    public static final double stepDiscount = 0.05;
    public static final double minDiscount = 0.0;

    public static List<OrderResult> calculateOrderResults(List<Order> orders) {
        Map<String, List<Order>> companyOrders = new HashMap<>();

        for (Order order : orders) {
            String companyName = order.getCompanyName();
            companyOrders.computeIfAbsent(companyName, k -> new ArrayList<>()).add(order);
        }

        for (List<Order> companyOrderList : companyOrders.values()) {
            companyOrderList.sort(Comparator.comparing(Order::getPurchaseDateTime));
        }

        List<OrderResult> results = new ArrayList<>();

        for (Map.Entry<String, List<Order>> entry : companyOrders.entrySet()) {
            String companyName = entry.getKey();
            List<Order> orderList = entry.getValue();

            double totalAmount = 0;
            double discountedAmount = 0;

            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                double orderAmount = order.getAmount();
                totalAmount += orderAmount;

                double discountRate =Math.max(minDiscount,baseDiscount - (i * stepDiscount));
                discountedAmount += orderAmount * (1 - discountRate);
            }
            results.add(new OrderResult(companyName, totalAmount));
        }
        return results;
    }
}
