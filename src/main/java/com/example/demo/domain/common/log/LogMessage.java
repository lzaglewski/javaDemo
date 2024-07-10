package com.example.demo.domain.common.log;


public class LogMessage {
    private String content;
    private long timestamp;

    public LogMessage(String content, long timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
