package com.spar3chang3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConfig {
    private static final String CONFIG_DIR = "./data/config.txt";

    private static final File configFile = new File(CONFIG_DIR);
    private static FileReader configReader;

    private static String logPath;
    private static String heapMetricPath;

    static {
        readConfig();
    }

    public static String getLogPath() {
        return logPath;
    }

    public static String getHeapMetricPath() {
        return heapMetricPath;
    }

    public static void readConfig() {
        StringBuilder currentLine = new StringBuilder();
        int character;
        char charToAppend;

        try {
            configReader = new FileReader(configFile);
            while ((character = configReader.read()) != -1) {
                charToAppend = (char) character;
                if (charToAppend == '\n') {
                    checkParams(currentLine.toString());
                    currentLine.delete(0, currentLine.length());
                } else {
                    currentLine.append(charToAppend);
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching/reading config directory");
            System.err.println("Fatal, exiting after stacktrace");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void checkParams(String toCheck) {
        toCheck = toCheck.trim();
        if (toCheck.isEmpty() || toCheck.startsWith("#")) {
            return;
        }
        String[] configParts = toCheck.split("=");
        if (configParts.length != 2) {
            return;
        }
        configParts[0] = configParts[0].trim(); //key
        configParts[1] = configParts[1].trim(); //value

        switch (configParts[0]) {
            case "logPath" -> {
                logPath = configParts[1].replace("\"", "");
            }
            case "heapMetricPath" -> {
                heapMetricPath = configParts[1].replace("\"", "");
            }
            default -> {
                System.out.println("ERR on line: " + toCheck);
            }
        }
    }

}
