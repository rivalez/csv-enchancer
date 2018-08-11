package com.tabor.files;

import java.util.Date;

public class Consumer implements Runnable {
    private final TempStorage tempStorage;
    private FileManager fileManager;

    public Consumer(TempStorage tempStorage, FileManager fileManager) {
        this.tempStorage = tempStorage;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        System.out.println("start " + new Date(System.currentTimeMillis()));
        Chunk chunk = new Chunk();
        while (GlobalState.isProcessing()) {
            String line = tempStorage.take();
            if (line.contains("ORDER")) {
                fileManager.save(chunk);
                chunk = new Chunk();
            }
            chunk.add(line);
        }
        System.out.println("that is all " + new Date(System.currentTimeMillis()));

    }
}
