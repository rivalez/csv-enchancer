package com.tabor.files;

public class Consumer implements Runnable {
    private final TempStorage tempStorage;
    private final FileManager fileManager;

    public Consumer(TempStorage tempStorage, FileManager fileManager) {
        this.tempStorage = tempStorage;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        Chunk chunk = new Chunk();
        while (shouldConsume()) {
            String line = tempStorage.take();
            if (line.contains("ORDER")) {
                fileManager.save(chunk);
                chunk = new Chunk();
            }
            chunk.add(line);
        }
    }

    private boolean shouldConsume() {
        return GlobalState.isProcessing() || tempStorage.isNotEmpty();
    }
}
