package com.salesystem.parser;

public class HashLineParser implements LineParser{
    @Override
    public String[] parseLine(String line) {
        return line.split("#");
    }
}