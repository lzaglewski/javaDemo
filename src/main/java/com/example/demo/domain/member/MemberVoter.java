package com.example.demo.domain.member;

import com.example.demo.application.common.voter.BaseVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class MemberVoter extends BaseVoter {

    public static final String VIEW = "SHOW";

    @Override
    public boolean check(String action, Object subject, String currentLoggedUser) {
        if (subject instanceof Member) {
            Member member = (Member) subject;
            return hasAccess(action, member);
        }
        return false;
    }

    @Override
    public void vote(String action, Object subject, String currentLoggedUser) throws AccessDeniedException {
        if (subject instanceof Member) {
            Member member = (Member) subject;
            if (!hasAccess(action, member)) {
                throw new AccessDeniedException("Access denied");
            }
        }
    }

    private boolean hasAccess(String action, Member member) {
        Authentication user = this.getLoggedUser();

        if (!action.equals(VIEW)) {
            return false;
        }

        return user.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

}
