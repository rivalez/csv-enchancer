package com.tabor.storage;

import com.tabor.files.Chunk;

public abstract class StorageTemplate {
    public abstract void save(Chunk chunk);
}
