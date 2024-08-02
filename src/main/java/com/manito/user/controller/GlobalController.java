package com.manito.user.controller;

import com.manito.user.dto.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession session;

    @ModelAttribute("user")
    public Member getLoggedInUser() {
        return (Member) session.getAttribute("user");
    }
}
