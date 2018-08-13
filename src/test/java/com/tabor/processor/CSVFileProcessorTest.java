package com.tabor.processor;

import com.tabor.files.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@RunWith(JUnit4.class)
public class CSVFileProcessorTest {
    private final String destPath = "dest";
    private final String filePath = "file.csv";
    private FileManager fileManager = new CSVFileManager();
    private TempStorage tempStorage = new TempStorage();
    private FileSizeScanner fileSizeScanner = new FileSizeScanner();
    private Producer producer = new Producer(tempStorage, filePath);
    private Consumer consumer = new Consumer(tempStorage, fileManager);
    private Processor processor = new CSVFileProcessor(fileManager, consumer, producer, fileSizeScanner);

    @Test
    public void processCSV_into10Files() {
        //assign
        int filesAmount = 10;
        //act
        processor.process(filePath, destPath, filesAmount);
        //assert
        await().atMost(10, TimeUnit.MINUTES)
                .until(() -> !tempStorage.isNotEmpty() && !GlobalState.isProcessing());

    }
}