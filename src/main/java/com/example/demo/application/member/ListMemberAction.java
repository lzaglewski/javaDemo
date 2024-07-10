package com.example.demo.application.member;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListMemberAction {

    private final MemberService memberService;

    public ListMemberAction(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/list")
    public String getMembersByEmail(Model model) {
        List<Member> members = this.memberService.getMembersByEmail("john@example.com");
        model.addAttribute("members", members);
        return "members_list";
    }

}
