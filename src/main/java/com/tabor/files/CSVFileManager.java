package com.tabor.files;

import com.tabor.model.Order;
import com.tabor.storage.BalancedSave;
import com.tabor.storage.RandomSave;
import com.tabor.storage.StorageTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class CSVFileManager implements FileManager {
    private static final String FILE = "file";
    private List<AvailableFile> storage;
    private StorageTemplate storageTemplate;

    public CSVFileManager(List<AvailableFile> storage) {
        this.storage = storage;
    }

    @Override
    public void save(Order order) {
        storageTemplate.save(order);
    }

    @Override
    public void initStorage(String dest, int amount, long lines) {
        IntStream.range(0, amount).forEach(nr -> {
            try {
                storage.add(new AvailableFile(FILE + nr + ".csv", dest));
            } catch (IOException e) {
                throw new RuntimeException("unable to initialize processing storage", e);
            }
        });
        setSavingStrategy(lines);
    }

    private void setSavingStrategy(long lines) {
        if (lines > 0) {
            final BalancedSave balancedSave = new BalancedSave(storage, lines);
            balancedSave.init();
            this.storageTemplate = balancedSave;
        } else {
            storageTemplate = new RandomSave(storage);
        }
    }
}
