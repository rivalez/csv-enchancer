package com.tabor.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class AvailableFile {
    private final String path;
    private BufferedWriter writer;
    private long freeLines;

    AvailableFile(String name, String path) throws IOException {
        this.path = path;
        writer = new BufferedWriter(new FileWriter(name, true));
    }

    public void write(Chunk chunk) {
        chunk.getLines().forEach(s -> {
            try {
                writer.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public long getFreeLines() {
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
