package com.tabor.files;

public interface FileManager {
    void save(Chunk chunk);

    void initStorage(String dest, int amount);
}
