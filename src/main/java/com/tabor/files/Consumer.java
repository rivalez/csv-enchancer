package com.tabor.files;

import com.tabor.model.Order;
import com.tabor.state.GlobalState;

public class Consumer implements Runnable {
    private static final String ORDER = "ORDER";
    private final TempStorage tempStorage;
    private final FileManager fileManager;

    public Consumer(TempStorage tempStorage, FileManager fileManager) {
        this.tempStorage = tempStorage;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        Order order = new Order();
        while (shouldConsume()) {
            String line = tempStorage.take();
            if (isNewOrder(line)) {
                fileManager.save(order);
                order = new Order();
            }
            order.add(line);
        }
    }

    private boolean isNewOrder(String line) {
        return line.contains(ORDER);
    }

    private boolean shouldConsume() {
        return GlobalState.isProcessing() || tempStorage.isNotEmpty();
    }
}
