package com.tabor.files;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

@RunWith(JUnit4.class)
public class FileSizeScannerTest {

    private FileSizeScanner fileSizeScanner = new FileSizeScanner();

    @Test
    public void shouldCountFileLines() {
        //act
        File file = new File("src/test/resources/dest/testfile.csv");
        long lines = fileSizeScanner.getLines(file.getAbsolutePath());
        //assert
        Assert.assertEquals(5, lines);

    }
}