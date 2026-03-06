package com.salesystem.model;

import java.time.LocalDateTime;

public class Order {

    private LocalDateTime purchaseDateTime;
    private String companyName;
    private double amountOfCement;

    public Order(LocalDateTime purchaseDateTime, String nameOfCompany,
                 double amountOfCement) {
        this.purchaseDateTime = purchaseDateTime;
        this.companyName = companyName;
        this.amountOfCement = amountOfCement;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }
    public String getCompanyName() {
        return companyName;
    }
    public double getAmount() {return amount;
    }

}
