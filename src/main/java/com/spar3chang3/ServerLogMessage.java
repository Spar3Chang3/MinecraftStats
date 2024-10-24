package com.spar3chang3;

public class ServerLogMessage {

    private String logMessage;

    public ServerLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public ServerLogMessage() {
        this("ERROR, NO MESSAGE PROVIDED");
    }

    public String getLogMessage() {
        return this.logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
}
