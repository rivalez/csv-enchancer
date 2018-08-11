package com.tabor.files;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Chunk {
    private List<String> lines = new ArrayList<>();

    public void add(String line) {
        lines.add(line);
    }

    public Stream<String> getLines() {
        return lines.stream();
    }
}
