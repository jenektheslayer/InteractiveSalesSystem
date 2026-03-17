package com.salesystem;


import com.salesystem.manager.FileOrderManager;
import com.salesystem.model.OrderResult;


public class Main {
    public static void main(String[] args) {
        String filePath;
        FileOrderManager fileOrderManager = new FileOrderManager();
        fileOrderManager.manage(
                "C:\\Users\\Андрей\\IdeaProjects\\sales_system\\InteractiveSalesSystem\\src\\main\\resources\\discount_day.txt",
                0.5, 0.05, 0.0
        );

    }
}
