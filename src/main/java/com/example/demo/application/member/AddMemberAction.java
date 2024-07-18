package com.example.demo.application.member;

import com.example.demo.domain.member.MemberService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMemberAction {

    private final MemberService memberService;

    private final EntityManagerFactory entityManagerFactory;

    public AddMemberAction(MemberService memberService, EntityManagerFactory entityManagerFactory) {
        this.memberService = memberService;
        this.entityManagerFactory = entityManagerFactory;
    }

    @GetMapping("/members/add")
    public String addMember() {
        var em = entityManagerFactory.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();

        this.memberService.createMember("John", "john@example.com");

        em.flush();
        transaction.commit();
        em.close();

        return "redirect:/members/list";
    }
}
