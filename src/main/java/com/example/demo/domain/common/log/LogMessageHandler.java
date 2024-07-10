package com.example.demo.domain.common.log;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class LogMessageHandler implements MessageHandler {

    private final LogEntryRepository logEntryRepository;

    public LogMessageHandler(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }



    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        LogMessage logMessage = (LogMessage) message.getPayload();
        LogEntry logEntry = new LogEntry(logMessage.getContent(), logMessage.getTimestamp());
        logEntryRepository.save(logEntry);
    }
}
