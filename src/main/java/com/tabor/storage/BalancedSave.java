package com.tabor.storage;

import com.tabor.files.AvailableFile;
import com.tabor.files.Chunk;

import java.util.List;

public class BalancedSave extends StorageTemplate {
    private final List<AvailableFile> storage;
    private final long lines;

    public BalancedSave(List<AvailableFile> storage, long lines) {
        this.storage = storage;
        this.lines = lines;
    }

    public void init() {
        long linesPerFile = lines / storage.size();
        storage.forEach(file -> file.setFreeLines(linesPerFile));
    }

    @Override
    public void save(Chunk chunk) {
        long lines = chunk.getLines().count();
        AvailableFile availableFile = storage.stream()
                .filter(file -> file.canAdd(lines))
                .findFirst()
                .orElse(storage.get(0));
        availableFile.decreaseFreeLines(lines);
        availableFile.write(chunk);
    }
}
