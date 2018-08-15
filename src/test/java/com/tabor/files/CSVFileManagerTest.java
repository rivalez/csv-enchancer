package com.tabor.files;

import com.tabor.model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class CSVFileManagerTest {

    private static final String SRC_TEST_RESOURCES_DEST = "src/test/resources/dest";
    private List<AvailableFile> list = new ArrayList<>();
    private FileManager csvFileManager = new CSVFileManager(list);


    @Before
    public void setUp() throws Exception {
        File file0 = new File("src/test/resources/dest/file0.csv");
        File file1 = new File("src/test/resources/dest/file1.csv");
        Files.deleteIfExists(file0.toPath());
        Files.deleteIfExists(file1.toPath());
    }

    @Test
    public void shouldInitStorage() {
        //act
        csvFileManager.initStorage(SRC_TEST_RESOURCES_DEST, 2, 1_000);
        //assert
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(500, list.get(0).getFreeLines());
    }

    @Test
    public void shouldSaveDataToFile() {
        //assign
        Order order = new Order();
        order.add("dasd");
        //act
        csvFileManager.initStorage(SRC_TEST_RESOURCES_DEST, 2, 1_000);
        csvFileManager.save(order);
        //assert
        Assert.assertEquals(499, list.get(0).getFreeLines());
    }

    @Test
    public void shouldSaveDataToFirstFile_IfOrderIsTooBig() {
        //assign
        Order order = new Order();
        for (int i = 0; i < 501; i++) {
            order.add(String.valueOf(i));
        }
        //act
        csvFileManager.initStorage(SRC_TEST_RESOURCES_DEST, 2, 1_000);
        csvFileManager.save(order);
        //assert
        Assert.assertEquals(-1, list.get(0).getFreeLines());
        Assert.assertEquals(500, list.get(1).getFreeLines());
    }



}