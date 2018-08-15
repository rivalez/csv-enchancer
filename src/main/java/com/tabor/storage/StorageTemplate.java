package com.tabor.storage;

import com.tabor.model.Order;

public abstract class StorageTemplate {
    public abstract void save(Order order);
}
