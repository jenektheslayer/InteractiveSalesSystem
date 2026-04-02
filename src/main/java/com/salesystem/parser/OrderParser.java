package com.salesystem.parser;

import com.salesystem.model.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final ParserFactory parserFactory;

    public OrderParser() {
        this.parserFactory = new ParserFactory();
    }

    public List<Order> parse(List<String> lines) {

        LineParser lineParser = parserFactory.getParserForFile(lines.get(0));
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = lineParser.parseLine(line);
            if (parts.length != 3) {
                throw new IllegalArgumentException("Некорректный формат" + line);
            }

            LocalDateTime purchaseDataTime = LocalDateTime.parse(parts[0],FORMATTER);
            String nameOfCompany = parts[1];
            double priceOfCement = Double.parseDouble(parts[2]);
            orders.add(new Order(purchaseDataTime, nameOfCompany, priceOfCement));
        }
        return orders;
    }
}