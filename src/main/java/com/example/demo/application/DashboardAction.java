package com.example.demo.application;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class DashboardAction {

    @GetMapping()
    public String home(Model model) {


        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            Object principal = authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            model.addAttribute("username", username);
            model.addAttribute("principal", principal);
            model.addAttribute("authorities", authorities);
        }

        return "dashboard_page";
    }
}
