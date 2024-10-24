package com.spar3chang3;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

public class LogFileTailer {

    private String logFilePath;
    private Tailer logTailer;
    private File logFile;
    private TailerListenerAdapter logListener;

    public LogFileTailer(String logFilePath, TailerListenerAdapter logListener) {
        this.logFilePath = logFilePath;
        this.logListener = logListener;
        setup();
    }

    public LogFileTailer(TailerListenerAdapter logListener) {
        this.logFilePath = FileConfiguration.getLogPath();
        this.logListener = logListener;
        setup();
    }

    private void setup() {
        logFile = new File(logFilePath);
        logTailer = new Tailer(logFile, logListener);
    }

    public void start() {
        logTailer.run();
    }
}
