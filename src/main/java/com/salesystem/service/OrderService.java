package com.salesystem.service;

import com.salesystem.model.Order;
import com.salesystem.model.OrderResult;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    public List<OrderResult> calculateOrderResults(
            List<Order> orders,
            double baseDiscount,
            double stepDiscount,
            double minDiscount,
            double price
    ) {
        orders.sort(Comparator.comparing(Order::getPurchaseDateTime));

        Map<String, Double> resultsMap = new HashMap<>();
        Map<String, Integer> orderCounts = new HashMap<>();
        List<String> results = new ArrayList<>();
        for (Order order : orders) {
            String companyName = order.getCompanyName();
            double orderAmount = order.getAmount();
            int currentCount = orderCounts.getOrDefault(companyName, 0);
            double currentDiscount = Math.max(baseDiscount - stepDiscount * currentCount, minDiscount);
            double discountedAmount = price * orderAmount * (1 - currentDiscount);
            double currentTotal = resultsMap.getOrDefault(companyName, 0.0);
            resultsMap.put(companyName, currentTotal + discountedAmount);
            orderCounts.put(companyName, currentCount + 1);
        }
        return resultsMap.entrySet().stream()
                .map(entry -> new OrderResult(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
