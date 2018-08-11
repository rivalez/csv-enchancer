package com.tabor.files;

import java.io.BufferedReader;
import java.io.FileReader;

public class Producer implements Runnable {
    private final TempStorage tempStorage;
    private final String inputFile;

    public Producer(TempStorage tempStorage, String inputFile) {
        this.tempStorage = tempStorage;
        this.inputFile = inputFile;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = br.readLine()) != null) {
                tempStorage.put(line);
            }
            GlobalState.processingFinished();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
