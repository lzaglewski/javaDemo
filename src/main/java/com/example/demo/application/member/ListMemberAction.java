package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import com.example.demo.application.common.mail.Mailer;

import jakarta.mail.MessagingException;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
final public class ListMemberAction {

    @Autowired
    private Mailer mailer;

    private final NotificationManager notificationManager;

    public ListMemberAction(
        NotificationManager notificationManager
    ) {
        this.notificationManager = notificationManager;
    }

    @GetMapping("/members/list")
    public String getMembersByEmail(Model model) throws MessagingException {
        notificationManager.add(NotificationType.SUCCESS.name(), "my success");

        var params = new HashMap<String, Object>();
        params.put("name", "Marek");
        params.put("message", "My secret message to you my friend");
        mailer.send("marek@javatestexample.com", "Hello my friend", "mail/hello", params);

        return "Member/list";
    }

}
