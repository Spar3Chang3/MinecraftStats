package com.spar3chang3;


import org.apache.commons.io.input.TailerListenerAdapter;

public class Main {
    public static final String LOGPATH = FileConfig.getLogPath();
    public static final String HEAPMETRICPATH = FileConfig.getHeapMetricPath();

    public static void main(String[] args) {

        var logBuffer = new Queue<String>();
        var heapBuffer = new Queue<String>();

        TailerListenerAdapter logListener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                logBuffer.enqueue(IpSanitizer.removeIp(line));
                System.out.println(line);
            }
        };

        TailerListenerAdapter heapListener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                heapBuffer.enqueue(IpSanitizer.removeIp(line));
            }
        };

        LogFileTailer logTailer = new LogFileTailer(LOGPATH, logListener);
        HeapFileTailer heapTailer = new HeapFileTailer(HEAPMETRICPATH, heapListener);


        logTailer.start();
        heapTailer.start();
    }
}