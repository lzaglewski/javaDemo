package com.example.demo.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeAction {

    @GetMapping()
    public String home(Model model) {
        return "home_page";
    }
}
