package com.example.demo.application.common.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintTime {
    @Scheduled(cron = "0 * * * * ?")
    public void printTime() {
        System.out.println("Scheduled test: Current time: " + System.currentTimeMillis());
    }
}
