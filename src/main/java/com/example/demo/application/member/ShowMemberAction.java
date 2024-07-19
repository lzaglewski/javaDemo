package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.member.MemberVoter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Controller
final public class ShowMemberAction {

    private final MemberService memberService;
    private final NotificationManager notificationManager;
    private final MemberVoter memberVoter;

    public ShowMemberAction(
            MemberService memberService,
            NotificationManager notificationManager,
            MemberVoter memberVoter) {
        this.memberService = memberService;
        this.notificationManager = notificationManager;
        this.memberVoter = memberVoter;
    }

    @GetMapping("/members/show/{uuid}")
    public String getMemberById(Model model, @PathVariable String uuid) throws AccessDeniedException {
        var member = this.memberService.getMember(uuid);
        this.memberVoter.vote("SHOW", member);
        model.addAttribute("member", member);
        notificationManager.add(NotificationType.SUCCESS.name(), "Member successfully showed");
        return "Member/show";
    }

}
