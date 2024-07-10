package com.example.demo.config;

import com.example.demo.domain.common.log.LogEntryRepository;
import com.example.demo.domain.common.log.LogMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class IntegrationConfig {

    @Bean
    public DirectChannel logChannel() {
        return new DirectChannel();
    }

//    @Bean
//    @ServiceActivator(inputChannel = "logChannel")
//    public MessageHandler logHandler() {
//        return new LogMessageHandler();
//    }

    @Bean
    @ServiceActivator(inputChannel = "logChannel")
    public MessageHandler logHandler(LogEntryRepository logEntryRepository) {
        return new LogMessageHandler(logEntryRepository);
    }

}
