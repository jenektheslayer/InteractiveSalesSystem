package com.salesystem.parser;

import com.salesystem.model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static Order parse(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректный формат" + line);
        }

        LocalDateTime purchaseDataTime = LocalDateTime.parse(parts[0],FORMATTER);
        String nameOfCompany = parts[1];
        double priceOfCement = Double.parseDouble(parts[2]);

        return new Order(purchaseDataTime, nameOfCompany, priceOfCement);
    }
}
