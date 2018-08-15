package com.tabor.storage;

import com.tabor.files.AvailableFile;
import com.tabor.model.Order;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSave extends StorageTemplate {
    private final List<AvailableFile> storage;

    public RandomSave(List<AvailableFile> storage) {
        this.storage = storage;
    }

    @Override
    public void save(Order order) {
        AvailableFile availableFile = storage.get(ThreadLocalRandom.current().nextInt(0, storage.size()));
        availableFile.write(order);
    }
}
