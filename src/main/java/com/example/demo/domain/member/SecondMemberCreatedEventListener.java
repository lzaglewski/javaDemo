package com.example.demo.domain.member;

import com.example.demo.domain.common.log.LogMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SecondMemberCreatedEventListener {

    @EventListener
    public void handleMemberCreatedEvent(MemberCreatedEvent event) {
        LogMessage logMessage = new LogMessage("Member created: " + event.getName() + ", " + event.getEmail(), System.currentTimeMillis());
        System.out.println("SecondMemberCreatedEventListener");
        System.out.println(logMessage);
    }

}
