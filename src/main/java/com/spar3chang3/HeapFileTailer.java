package com.spar3chang3;

import java.io.File;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.apache.commons.io.input.Tailer;

public class HeapFileTailer {

    private String heapFilePath;
    private TailerListenerAdapter heapListener;
    private Tailer heapTailer;
    private File heapFile;

    public HeapFileTailer(String heapFilePath, TailerListenerAdapter heapListener) {
        this.heapFilePath = heapFilePath;
        this.heapListener = heapListener;
        setup();
    }

    public HeapFileTailer(TailerListenerAdapter heapListener) {
        this.heapFilePath = FileConfig.getHeapMetricPath();
        this.heapListener = heapListener;
        setup();
    }

    private void setup() {
        heapFile = new File(heapFilePath);
        heapTailer = new Tailer(heapFile, heapListener);
    }

    public void start() {
        heapTailer.run();
    }
}
