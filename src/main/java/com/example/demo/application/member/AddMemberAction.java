package com.example.demo.application.member;

import com.example.demo.application.common.flash.NotificationManager;
import com.example.demo.application.common.flash.NotificationType;
import com.example.demo.domain.member.MemberService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddMemberAction {

    private final MemberService memberService;

    private final EntityManagerFactory entityManagerFactory;

    private final NotificationManager notificationManager;

    public AddMemberAction(MemberService memberService, EntityManagerFactory entityManagerFactory, NotificationManager notificationManager) {
        this.memberService = memberService;
        this.entityManagerFactory = entityManagerFactory;
        this.notificationManager = notificationManager;
    }

    @PostMapping("/members/add")
    public String addMember(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            notificationManager.add(NotificationType.DANGER.name(), "Member not added");
            model.addAttribute("memberForm", memberForm);
            return "Member/add";
        }
        var em = entityManagerFactory.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();

        this.memberService.createMember(memberForm.getName(), memberForm.getEmail());

        em.flush();
        transaction.commit();
        em.close();

        return "redirect:/members/list";
    }

    @GetMapping("/members/add")
    public String showMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "Member/add";
    }
}
