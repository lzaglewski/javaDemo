package com.example.demo.application.common.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintTime {
    int counter = 0;

    @Scheduled(cron = "0 * * * * ?")
    public void printTime() {
        //this method will print couter every minute

        System.out.println("Scheduled test: Counter:" + counter++);
    }
}
