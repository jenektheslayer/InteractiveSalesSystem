package com.salesystem.parser;

import static com.salesystem.parser.OrderDelimeter.PIPE_DELIMETER;

public class PipeLineParser implements LineParser{
    @Override
    public String[] parseLine(String line) {
        return line.split(PIPE_DELIMETER);
    }
}

