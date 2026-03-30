package com.salesystem.model;

import java.time.LocalDateTime;

public class Order {

    private LocalDateTime purchaseDateTime;
    private String companyName;
    private double amount;

    public Order(LocalDateTime purchaseDateTime, String companyName,
                 double amount) {
        this.purchaseDateTime = purchaseDateTime;
        this.companyName = companyName;
        this.amount = amount;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getAmount() {
        return amount;
    }
    public int minElement(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

}