package com.example.demo.application.common.flash;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Service
final public class NotificationManager {
    private final Map<String, ArrayList<String>> messages;

    public NotificationManager() {
        messages = new TreeMap<>();
        for (NotificationType flashType: NotificationType.values()) {
            messages.put(flashType.name(), new ArrayList<>());
        }
    }

    public void add(String key, String message) {
        var messagesType = messages.computeIfAbsent(key, k -> new ArrayList<>());

        messagesType.add(message);
    }

    public ArrayList<String> get(String key) {
        var messagesType = messages.remove(key);
        if (messagesType == null) {
            return new ArrayList<>();
        }

        return messagesType;
    }
}
