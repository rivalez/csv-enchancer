package com.tabor.processor;

import com.tabor.files.Consumer;
import com.tabor.files.FileManager;
import com.tabor.files.FileSizeScanner;
import com.tabor.files.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CSVFileProcessor implements Processor {
    private final FileManager fileManager;
    private final ExecutorService producerService = Executors.newSingleThreadExecutor();
    private final ScheduledExecutorService consumerService = Executors.newSingleThreadScheduledExecutor();
    private final Consumer consumer;
    private final Producer producer;
    private final FileSizeScanner fileSizeScanner;

    CSVFileProcessor(FileManager fileManager, Consumer consumer, Producer producer, FileSizeScanner fileSizeScanner) {
        this.fileManager = fileManager;
        this.consumer = consumer;
        this.producer = producer;
        this.fileSizeScanner = fileSizeScanner;
    }

    @Override
    public void process(String file, String dest, int amount) {
        long lines = fileSizeScanner.getLines(file);
        fileManager.initStorage(dest, amount, lines);
        producerService.execute(producer);
        consumerService.schedule(consumer, 1, TimeUnit.MILLISECONDS);
    }
}
