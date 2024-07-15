package com.example.demo.domain.member;

import org.springframework.context.ApplicationEvent;

public class MemberCreatedEvent extends ApplicationEvent {
    private final String name;
    private final String email;

    public MemberCreatedEvent(Object source, String name, String email) {
        super(source);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
