package com.tabor.files;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TempStorage {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(200_000);

    void put(String line) {
        try {
            queue.put(line);
        } catch (InterruptedException e) {
            //any kind of logging
            e.printStackTrace();
        }
    }

    String take() {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                //any kind of logging
                e.printStackTrace();
            }
        return "";
    }

    public boolean isNotEmpty() {
        return queue.size() != 0;
    }

}
