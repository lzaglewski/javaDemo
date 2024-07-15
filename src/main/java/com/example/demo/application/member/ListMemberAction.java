package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
final public class ListMemberAction {

    private final MemberService memberService;
    private final NotificationManager notificationManager;

    public ListMemberAction(
        MemberService memberService,
        NotificationManager notificationManager
    ) {
        this.memberService = memberService;
        this.notificationManager = notificationManager;
    }

    @GetMapping("/members/list")
    public String getMembersByEmail(Model model) {
        List<Member> members = this.memberService.getMembersByEmail("john@example.com");
        model.addAttribute("members", members);

        notificationManager.add(NotificationType.SUCCESS.name(), "my success");

        return "members_list";
    }

}
