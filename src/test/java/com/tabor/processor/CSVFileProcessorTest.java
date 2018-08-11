package com.tabor.processor;

import com.tabor.files.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CSVFileProcessorTest {
    private final String destPath = "dest";
    private final String filePath = "file.csv";

    private FileManager fileManager = new CSVFileManager();
    private TempStorage tempStorage = new TempStorage();
    private Producer producer = new Producer(tempStorage, filePath);
    private Consumer consumer = new Consumer(tempStorage, fileManager);
    private Processor processor = new CSVFileProcessor(fileManager, consumer, producer);

    @Test
    public void processCSV_into10Files() {
        int filesAmount = 10;
        processor.process(destPath, filesAmount);
    }
}