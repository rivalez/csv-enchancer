package com.tabor.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public final class AvailableFile {
    private final String name;
    private final String path;
    private BufferedWriter writer;

    public AvailableFile(String name, String path) throws IOException {
        this.name = name;
        this.path = path;
        writer = new BufferedWriter(new FileWriter(name, true));
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void write(Chunk chunk) {
        Stream<String> lines = chunk.getLines();
        lines.forEach(s -> {
            try {
                writer.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
