package com.tabor.state;

import java.util.concurrent.atomic.AtomicBoolean;

public class GlobalState {
    private static AtomicBoolean processing = new AtomicBoolean(true);

    public static boolean isProcessing() {
        return processing.get();
    }

    public static void processingFinished() {
        processing.set(false);
    }
}
