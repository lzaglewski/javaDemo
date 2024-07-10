package com.example.demo.domain.common.log;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class LogEntry {
    @Id
    private UUID id;
    private String content;
    private long timestamp;

    LogEntry(String content, long timestamp) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.timestamp = timestamp;
    }

    public LogEntry() {
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
