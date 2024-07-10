package com.example.demo.application.member;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/hello")
    public String helloMember() {
        return "Member created";
    }

    @GetMapping("/members/add")
    public String addMember() {
        this.memberService.createMember("John", "john@example.com");
        return "Member created";
    }

    @GetMapping("/members")
    public List<Member> getMembersByEmail() {
        return this.memberService.getMembersByEmail("john@example.com");
    }

    @GetMapping("/members/whoami")
    public String whoAmI() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return username + " " + principal + " " + authorities;
    }
}
