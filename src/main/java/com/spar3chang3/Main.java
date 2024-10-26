package com.spar3chang3;


import org.apache.commons.io.input.TailerListenerAdapter;

public class Main {
    public static final String LOGPATH = FileConfig.getLogPath();

    public static void main(String[] args) {

        var buffer = new Queue<String>();

        TailerListenerAdapter logListener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                buffer.enqueue(IpSanitizer.removeIp(line));
                System.out.println(line);
            }
        };

        LogFileTailer logTailer = new LogFileTailer(LOGPATH, logListener);
        logTailer.start();
    }
}