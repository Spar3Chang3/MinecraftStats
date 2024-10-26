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

    private static void readConfig() {
        StringBuilder currentLine = new StringBuilder();
        int character;
        char charToAppend;
        boolean continueReading = true;

        try {
            configReader = new FileReader(configFile);
            while ((character = configReader.read()) != -1 && continueReading) {
                charToAppend = (char) character;

                if (charToAppend == '\n') {
                    System.out.println("Latest line: " + currentLine.toString());
                    continueReading = checkParams(currentLine);
                } else {
                    System.out.println("Latest char: " + charToAppend);
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

    private static boolean checkParams(StringBuilder toCheck) {
        if (toCheck.toString().startsWith("#")) {
            toCheck.delete(0, toCheck.length());
            return true;
        } else return switch (toCheck.substring(0, toCheck.indexOf("=")).replace(" ", "")) {
            case "logPath" -> {
                logPath = toCheck.substring(toCheck.indexOf("=") + 1, toCheck.length());
                yield true;
            }
            case "heapMetricPath" -> {
                heapMetricPath = toCheck.substring(toCheck.indexOf("=") + 1, toCheck.length());
                yield false;
            }
            default -> true;
        };
    }

}
