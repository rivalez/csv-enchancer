package com.tabor.files;

import com.tabor.model.Order;

public interface FileManager {
    void save(Order order);

    void initStorage(String dest, int amount, long lines);
}
