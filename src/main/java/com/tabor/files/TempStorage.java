package com.tabor.files;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class TempStorage {
    private static final String EMPTY = "";
    private final Logger logger = Logger.getLogger(TempStorage.class.getName());
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(200_000);

    void put(String line) {
        try {
            queue.put(line);
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        }
    }

    String take() {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                logger.warning(e.getMessage());
            }
        return EMPTY;
    }

    public boolean isNotEmpty() {
        return queue.size() != 0;
    }

}
