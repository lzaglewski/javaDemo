package com.example.demo.domain.common.log;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import io.micrometer.common.lang.NonNull;

public class LogMessageHandler implements MessageHandler {

    private final LogEntryRepository logEntryRepository;

    public LogMessageHandler(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }



    @Override
    public void handleMessage(@SuppressWarnings("null") @NonNull Message<?> message) throws MessagingException {
        LogMessage logMessage = (LogMessage) message.getPayload();
        LogEntry logEntry = new LogEntry(logMessage.getContent(), logMessage.getTimestamp());
        logEntryRepository.save(logEntry);
    }
}
