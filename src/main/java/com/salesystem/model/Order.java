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
}