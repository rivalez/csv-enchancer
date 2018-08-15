package com.tabor.files;

import com.tabor.state.GlobalState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

public class Producer implements Runnable {
    private final Logger logger = Logger.getLogger(Producer.class.getName());
    private final TempStorage tempStorage;
    private final String inputFile;

    public Producer(TempStorage tempStorage, String inputFile) {
        this.tempStorage = tempStorage;
        this.inputFile = inputFile;
    }

    @Override
    public void run() {
        try {
            logger.info("Start processing file...");
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = br.readLine()) != null) {
                tempStorage.put(line + "\n");
            }
            GlobalState.processingFinished();
        } catch (java.io.IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
