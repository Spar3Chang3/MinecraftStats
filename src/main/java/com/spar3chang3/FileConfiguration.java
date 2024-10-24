package com.spar3chang3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConfiguration {
    private static final String CONFIG_DIR = "./data/config.txt";

    private static final File configFile = new File(CONFIG_DIR);
    private static FileReader configReader;

    public static String getLogPath() {
        StringBuilder logPath = new StringBuilder();
        int character;
        char charToAppend;
        boolean continueReading = true;

        try {
            configReader = new FileReader(configFile);

            while ((character = configReader.read()) != -1 && continueReading) {
                charToAppend = (char) character;

                if (charToAppend == '\n') {
                    continueReading = checkParams(logPath);
                } else {
                    logPath.append(charToAppend);
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching/reading config directory");
            System.err.println("Fatal, exiting after stacktrace");
            e.printStackTrace();
            System.exit(1);
        }

        return logPath.substring(logPath.indexOf("\"")+1, logPath.lastIndexOf("\""));
    }


    private static boolean checkParams(StringBuilder toCheck) {
        if (toCheck.toString().startsWith("#")) {
            toCheck.delete(0, toCheck.length());
            return true;
        } else return !toCheck.toString().startsWith("logPath");
    }

}
