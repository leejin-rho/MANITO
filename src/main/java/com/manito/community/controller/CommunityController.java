package com.manito.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @GetMapping
    public String communityBoard() {
        System.out.println("GET community");
        return "community/board";
    }

    @RequestMapping("/create")
    public String communityCreate() {
        System.out.println("POST community list");
        return "community/create";
    }

//    @PostMapping("/create")
//    public String createPost(Post post) {
//        postService.savePost(post);
//        return "redirect:/community";
//    }


    // 게시물 상세 페이지
//    @GetMapping("/post/{id}")
//    public String viewPost(@PathVariable("id") Long postId, Model model) {
//        Post post = postService.getPostById(postId);
//        List<Comment> comments = commentService.getCommentsByPostId(postId);
//        model.addAttribute("post", post);
//        model.addAttribute("comments", comments);
//        return "community/post"; // community/post.html
//    }


}
