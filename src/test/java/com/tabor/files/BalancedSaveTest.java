package com.tabor.files;

import com.tabor.storage.BalancedSave;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class BalancedSaveTest {

    private BalancedSave balancedSave;
    private List<AvailableFile> files;

    @Before
    public void setUp() {
        long lines = 100_000;
        files = IntStream.range(0, 10)
                        .mapToObj(f -> {
                            try {
                                return new AvailableFile("file " + f,"path " + f);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());
        this.balancedSave = new BalancedSave(files, lines);
    }


    @Test
    public void shouldDivideSpaceEqually() {
        //act
        balancedSave.init();
        //assert
        files.forEach(f ->
                Assert.assertEquals(10_000, f.getFreeLines()));
    }
    @Test
    public void shouldDecreaseAmountOfFreeSpace() {
        //assign
        Chunk chunk = new Chunk();
        Stream.generate(() -> "\n").limit(10_000)
                .collect(Collectors.toList())
                .forEach(chunk::add);
        //act
        balancedSave.init();
        balancedSave.save(chunk);
        //assert
        AvailableFile firstFile = files.get(0);
        AvailableFile nextFile = files.get(1);
        Assert.assertEquals(0, firstFile.getFreeLines());
        Assert.assertEquals(10_000, nextFile.getFreeLines());

    }
}