package com.salesystem.parser;

public class ParserFactory {
    public LineParser getParserForFile(String firstLine) {
        if (firstLine.contains("|")) {
            return new PipeLineParser();
        } else if (firstLine.contains("#")) {
            return new HashLineParser();
        } else {
            throw new IllegalArgumentException("Неопознанный разделитель");
        }
    }
}