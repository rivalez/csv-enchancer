package com.tabor.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CSVFileManager implements FileManager {
    private List<AvailableFile> storage = new ArrayList<>();

    @Override
    public void save(Chunk chunk) {
        AvailableFile availableFile = storage.get(ThreadLocalRandom.current().nextInt(0,10));
        availableFile.write(chunk);
    }

    @Override
    public void initStorage(String dest, int amount) {
        String file = "file";
        for (int i = 0; i < amount; i++) {
            try {
                storage.add(new AvailableFile(file + i + ".csv", dest));
            } catch (IOException e) {
                throw new RuntimeException("unable to initialize processing storage");
            }
        }
    }
}
