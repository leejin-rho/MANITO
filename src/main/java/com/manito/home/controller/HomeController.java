package com.manito.home.controller;

import com.manito.community.domain.Post;
import com.manito.community.service.CommunityService;
import com.manito.community.service.ReplyService;
import com.manito.user.dto.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CommunityService communityService;
    @Autowired
    ReplyService replyService;
    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String getPostsHome(Model model) {
        try {
            Member member = (Member) session.getAttribute("user");
            if (member == null) {
                return "redirect:/login";
            }

            int userId = member.getUid();
            List<Post> posts = communityService.getPostsByUserId(userId);

            for (Post post : posts) {
                int replyCount = replyService.getReplyCountForPost(post.getPid());
                post.setReplyNum(replyCount);
            }
            model.addAttribute("posts", posts);
            System.out.println(posts);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }
}
