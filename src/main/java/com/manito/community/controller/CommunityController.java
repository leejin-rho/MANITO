package com.manito.community.controller;

import com.manito.community.domain.Post;
import com.manito.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Date;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService service;

    @GetMapping
    public String communityBoard(Model model) throws Exception {
        System.out.println("GET community");
        // model의 key값이 데이터에 들어간다.
//        model.addAttribute("posts", "Community");
        model.addAttribute("posts", service.getAllPosts());
        return "community/board";
    }

    @RequestMapping("/create")
    public String communityCreate() {
        System.out.println("POST community");
        return "community/create";
    }

    @PostMapping("/create")
    public String createPost(Post post) throws Exception {
        try {
            System.out.println("successfully create community list");
            service.createPost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/community";
    }


    // 게시물 상세 페이지
    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") int postId, Model model) throws Exception {
        try {
            Post post = service.getPostById(postId);
//        List<Comment> comments = commentService.getCommentsByPostId(postId);
            model.addAttribute("post", post);
//        model.addAttribute("comments", comments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "community/post"; // community/post.html
    }


}
