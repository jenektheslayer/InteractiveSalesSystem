package com.salesystem.service;

import com.salesystem.model.OrderResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOrderService {
    public List<String> read(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException error) {
            throw new IORuntimeException("Невозможно прочитать файл: " + error.getMessage(), error);
        }
        return lines;
    }
    public void save(List<OrderResult> orderResults, String fileName) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("company,totalPrice");
            for (OrderResult orderResult : orderResults) {
                writer.println(orderResult.getCompanyName() + " " + orderResult.getTotalPrice());
            }
        } catch (IOException error) {
            throw new IORuntimeException("Невозможно прочитать файл: " + error.getMessage(), error);
        }
    }
}
