package com.tabor.processor;

import com.tabor.files.*;
import com.tabor.state.GlobalState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@RunWith(JUnit4.class)
public class CSVFileProcessorTest {
    private final String destPath = "src/test/resources";
    private final String inputFilePath = "file.csv";
    private FileManager fileManager = new CSVFileManager(new ArrayList<>());
    private TempStorage tempStorage = new TempStorage();
    private FileSizeScanner fileSizeScanner = new FileSizeScanner();
    private Producer producer = new Producer(tempStorage, inputFilePath);
    private Consumer consumer = new Consumer(tempStorage, fileManager);
    private Processor processor = new CSVFileProcessor(fileManager, consumer, producer, fileSizeScanner);

    @Test
    public void processCSV_into10Files() {
        //assign
        int filesAmount = 10;
        //act
        processor.process(inputFilePath, destPath, filesAmount);
        //assert
        await().atMost(10, TimeUnit.MINUTES)
                .until(() -> !tempStorage.isNotEmpty() && !GlobalState.isProcessing());

    }
}