package com.example.demo.application.member;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;

@Controller
final public class ListMemberPageAction {
    private final MemberService memberService;
    
    public ListMemberPageAction(
        MemberService memberService
    ) {
        this.memberService = memberService;
    }

    @GetMapping("/members/list/page")
    public String getPaginationMembers(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        var pageable = PageRequest.of(page - 1, size);
        var members = this.memberService.list(pageable);
        model.addAttribute("pagination", members);

        int totalPages = members.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "Member/list.page";
    }
}
