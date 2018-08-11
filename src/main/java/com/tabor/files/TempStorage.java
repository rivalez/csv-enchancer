package com.tabor.files;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TempStorage {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(100_000);

    public void put(String line) {
        try {
            queue.put(line);
        } catch (InterruptedException e) {
            //any kind of logging
            e.printStackTrace();
        }
    }

    public String take() {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                //any kind of logging
                e.printStackTrace();
            }
        return "";
    }

}
