package com.manito.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class UserController {

    @GetMapping
    public String getMypage(Model model)  {
        return "mypage/mypage";
    }
}
