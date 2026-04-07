package com.salesystem.service;

import com.salesystem.exception.IORuntimeException;
import com.salesystem.model.OrderResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderServiceTest {

    @TempDir
    Path tempDir;

    private FileOrderService fileOrderService = new FileOrderService();

    @Test
    void shouldReadFileContentCorrectly() throws IOException {
        Path testFile = tempDir.resolve("test-orders.txt");

        Files.write(testFile, List.of("order line 1", "order line 2", "order line 3"));

        List<String> result = fileOrderService.read(testFile.toString());

        assertEquals(result.size(),3);

        assertEquals("order line 1", result.get(0));

        assertEquals("order line 2", result.get(1));

        assertEquals("order line 3", result.get(2));
    }

    @Test
    void shouldSaveFileContentCorrectly() throws IOException {
        List<OrderResult> orderResults = List.of(
                new OrderResult("Company 1", 100.0),
                new OrderResult("Company 2", 200.0)
        );

        Path outputFile = tempDir.resolve("output.txt");

        fileOrderService.save(orderResults, outputFile.toString());

        List<String> savedContent = Files.readAllLines(outputFile);

        assertEquals(savedContent.size() - 1, 2 );

        assertTrue(savedContent.get(1).contains("Company 1"));

        assertTrue(savedContent.get(1).contains("100.0"));

        assertTrue(savedContent.get(2).contains("Company 2"));

        assertTrue(savedContent.get(2).contains("200.0"));
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() throws IOException {
        String notExistPath = tempDir.resolve("notexistent.txt").toString();

        assertThrows(IORuntimeException.class, () ->
                fileOrderService.read(notExistPath)
        );
    }

    @Test
    void shouldCreateFileWhenSaving() throws IOException {
        List<OrderResult> orderResults = List.of(new OrderResult("Company 1", 100.0));

        Path newFile = tempDir.resolve("newfile.txt");

        assertFalse(Files.exists(newFile));

        fileOrderService.save(orderResults, newFile.toString());

        assertTrue(Files.exists(newFile));

        assertTrue(Files.size(newFile) > 0);
    }

    @Test
    void shouldReturnEmptyListForFile() throws IOException {
        Path emptyFile = tempDir.resolve("empty.txt");

        Files.createFile(emptyFile);

        List<String> result = fileOrderService.read(emptyFile.toString());


        assertTrue(result.isEmpty());
    }
}
