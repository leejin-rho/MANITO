package com.manito.user.controller;

import com.manito.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("mypage")
    public String getMypage(Model model)  {
        return "mypage/mypage";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model)  {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login_id, @RequestParam String password, Model model) {
        boolean isAuthenticated = memberService.authenticate(login_id, password);

        if (isAuthenticated) {
            return "redirect:/"; // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login"; // 로그인 실패 시 로그인 페이지로 이동
        }
    }

    @GetMapping("/404")
    public String errorPage(Model model)  {
        return "/404";
    }
}
