package com.spar3chang3;

public class LogMessage {

    private String content;

    public LogMessage(String content) {
        this.content = content;
    }

    public LogMessage() {
        this("ERROR, NO MESSAGE PROVIDED");
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
