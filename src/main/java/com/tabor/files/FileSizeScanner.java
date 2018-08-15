package com.tabor.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FileSizeScanner {
    private Logger logger = Logger.getLogger(FileSizeScanner.class.getName());

    public long getLines(String file) {
        try {
            logger.info("Counting number of lines");
            return Files.lines(Paths.get(file)).count();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return 0;
    }
}
