package com.tabor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(JUnit4.class)
public class FileGeneratorTest {

    @Test
    public void generateCSV() throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter("file.csv", "UTF-8")) {
            for (int i = 0; i < 100_000; i++) {
                writer.println(String.format("ORDER,%s,...", i));
                for (int j = 0; j < (ThreadLocalRandom.current().nextInt(1000, 100_000)); j++) {
                    writer.println(String.format("POSITION,%s,...", j));
                }
            }
        }
    }
}
