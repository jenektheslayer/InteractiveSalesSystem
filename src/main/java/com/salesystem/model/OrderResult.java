package com.salesystem.model;

public class OrderResult {
    private String companyName;
    private double totalPrice;

    public OrderResult(String companyName, double totalPrice) {
        this.companyName = companyName;
        this.totalPrice = totalPrice;
    }


    public String getCompanyName() {
        return companyName;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

}
