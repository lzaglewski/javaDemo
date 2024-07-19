package com.example.demo.application.common.voter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public abstract class BaseVoter {

    public abstract boolean check(String action, Object subject, String currentLoggedUser);

    public boolean check(String action, Object subject) {
        return check(action, subject, null);
    }

    public abstract void vote(String action, Object subject, String currentLoggedUser) throws AccessDeniedException;

    public void vote(String action, Object subject) throws AccessDeniedException {
        vote(action, subject, null);
    }

    protected Authentication getLoggedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }
}
