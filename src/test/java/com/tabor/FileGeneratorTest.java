package com.tabor;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Test created for generation big csv files.
 * In current version
 * you need around 2 minutes for generation of 2.5GB file.
 */

@RunWith(JUnit4.class)
public class FileGeneratorTest {

    @Test
    @Ignore("Run when you want to generate 2.5GB csv file")
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
