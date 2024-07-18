package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import com.example.demo.domain.member.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getMembersByEmail(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "3") Integer size) {
        var pageable = PageRequest.of(page, size);
        var members = this.memberService.list(pageable);
        model.addAttribute("members", members);

        notificationManager.add(NotificationType.SUCCESS.name(), "my success");

        return "Member/list";
    }

}
