package com.salesystem.parser;

import static com.salesystem.parser.OrderDelimeter.HASH_DELIMETER;

public class HashLineParser implements LineParser{
    @Override
    public String[] parseLine(String line) {
        return line.split(HASH_DELIMETER);
    }
}