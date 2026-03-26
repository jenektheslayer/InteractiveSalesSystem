package com.salesystem.parser;

public class PipeLineParser implements LineParser{
    @Override
    public String[] parseLine(String line) {
        return line.split("\\|");
    }
}

