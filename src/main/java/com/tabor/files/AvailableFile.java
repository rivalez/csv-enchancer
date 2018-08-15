package com.tabor.files;

import com.tabor.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public final class AvailableFile {
    private static final Logger logger = Logger.getLogger(AvailableFile.class.getName());
    private final BufferedWriter writer;
    private long freeLines;

    AvailableFile(String name, String path) throws IOException {
        final String fullPath = String.format("%s/%s", path, name);
        writer = new BufferedWriter(new FileWriter(fullPath, true));
    }

    public void write(Order order) {
        order.getLines().forEach(s -> {
            try {
                writer.write(s);
            } catch (IOException e) {
                logger.warning("Cannot write order to file: " + e.getMessage());
            }
        });
    }

    long getFreeLines() {
        return freeLines;
    }

    public void decreaseFreeLines(long amount) {
        this.freeLines = freeLines - amount;
    }

    public void setFreeLines(long freeLines) {
        this.freeLines = freeLines;
    }

    public boolean canAdd(long amount) {
        return (this.freeLines - amount) >= 0;
    }
}
