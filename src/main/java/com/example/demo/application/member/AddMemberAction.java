package com.example.demo.application.member;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddMemberAction {

    private final MemberService memberService;

    public AddMemberAction(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/list")
    public List<Member> getMembersByEmail() {
        return this.memberService.getMembersByEmail("john@example.com");
    }
}
