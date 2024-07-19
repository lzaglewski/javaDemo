package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
final public class ListMemberAction {

    private final NotificationManager notificationManager;

    public ListMemberAction(
        NotificationManager notificationManager
    ) {
        this.notificationManager = notificationManager;
    }

    @GetMapping("/members/list")
    public String getMembersByEmail(Model model) {
        notificationManager.add(NotificationType.SUCCESS.name(), "my success");

        return "Member/list";
    }

}
