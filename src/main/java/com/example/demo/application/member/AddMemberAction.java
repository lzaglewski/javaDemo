package com.example.demo.application.member;

import com.example.demo.domain.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AddMemberAction {

    private final MemberService memberService;

    public AddMemberAction(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/add")
    public String addMember() {
        this.memberService.createMember("John", "john@example.com");
        return "redirect:/members/list";
    }
}
