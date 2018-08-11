package com.tabor.processor;

import com.tabor.files.Consumer;
import com.tabor.files.FileManager;
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

    public CSVFileProcessor(FileManager fileManager, Consumer consumer, Producer producer) {
        this.fileManager = fileManager;
        this.consumer = consumer;
        this.producer = producer;
    }

    @Override
    public void process(String dest, int amount) {
        fileManager.initStorage(dest, amount);
        producerService.execute(producer);
        consumerService.schedule(consumer, 1, TimeUnit.MILLISECONDS);
        while(true) {

        }
    }
}
