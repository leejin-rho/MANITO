package com.manito.community.controller;

import com.manito.community.domain.Post;
import com.manito.community.domain.Reply;
import com.manito.community.service.CommunityService;
import com.manito.community.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;
    @Autowired
    ReplyService replyService;

    @GetMapping
    public String communityBoard(Model model) throws Exception {
        System.out.println("GET community");
        model.addAttribute("posts", communityService.getAllPosts());
        return "community/board";
    }

    @GetMapping("/create")
    public String communityCreate() {
        System.out.println("POST Form");
        return "community/post-form";
    }

    @PostMapping("/create")
    public String createPost(Post post) throws Exception {
        try {
            communityService.createPost(post);
            System.out.println("Successfully CREATE community post !!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/community";
    }

    // 게시물 상세 페이지
    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") int postId, Model model) throws Exception {
        try {
            Post post = communityService.getPostById(postId);
            System.out.println(post);
            model.addAttribute("post", communityService.getPostById(postId));
            model.addAttribute("replies", replyService.getAllRepliesByPostId(postId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "community/post-detail";
    }


    @PostMapping("/post/{pid}/comment")
    public String createReply(@PathVariable("pid") int postId, @RequestParam("msg") String msg) throws Exception {
        System.out.println(postId);
        Reply reply = new Reply();
        reply.setPid(postId);
        reply.setUid(1);
        reply.setMsg(msg);

        replyService.createReply(reply);

        return "redirect:/community/post/" + postId; // 댓글 추가 후 게시물 상세 페이지로 리디렉션
    }

    // 상세 페이지 수정 / 삭제
    // 수정
    @GetMapping("/post/update")
    public String getUpdatePost(@RequestParam("pid") int postId,
                         Model model) {
        System.out.println(postId);
        try {
            model.addAttribute("post", communityService.getPostById(postId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "/community/post-form";
    }

    @PostMapping("/post/update")
    public String updatePost(Post post) {
        try {
            communityService.updatePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/community/post/" + post.getPid();
    }

    // 삭제
    @GetMapping("/post/delete")
    public String deletePost(@RequestParam("pid") int postId) throws Exception {
        try {
            communityService.deletePost(postId);
            System.out.println("Successfully DELETE community post !!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/community";
    }


}
