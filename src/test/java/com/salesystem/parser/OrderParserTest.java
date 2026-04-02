package com.salesystem.parser;

import com.salesystem.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderParserTest {

    @Test
    void parseLineCorrectLinesAndCreatingOrders() {

        List<String> lines = List.of(
                "2026-04-02T14:00:00|Sberbank|1100.0",
                "2026-04-02T15:00:00|Yandex|2200.0"
        );
        OrderParser orderParser = new OrderParser();

        List<Order> orders = orderParser.parse(lines);

        assertEquals(orders.size(), 2);

        assertEquals(orders.get(0).getCompanyName(),"Sberbank");

        assertEquals(orders.get(1).getAmount(), 2200.0);

    }

    @Test
    void parseHashLineCorrectLinesAndCreatingOrders() {

        List<String> lines = List.of(
                "2026-04-02T14:00:00#Apple#1100.0",
                "2026-04-02T15:00:00#Google#2200.0"
        );
        OrderParser orderParser = new OrderParser();

        List<Order> orders = orderParser.parse(lines);

        assertEquals(orders.size(), 2);

        assertEquals(orders.get(0).getCompanyName(),"Apple");

        assertEquals(orders.get(1).getAmount(), 2200.0);
    }

    @Test
    void parseInvalidLine() {

        List<String> lines = List.of("2026-04-02T14:00:00,Company 1");

        OrderParser orderParser = new OrderParser();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> orderParser.parse(lines)
        );

    }

    @Test
    void parseInvalidDateTimeFormatThrows() {

        List<String> lines = List.of("yyyy-aa-bbT12:00:00,Company 1,100.0");

        OrderParser orderParser = new OrderParser();

        assertThrows(
                IllegalArgumentException.class,
                () -> orderParser.parse(lines),
                "Неправильный формат ввода даты"
        );
    }
}
