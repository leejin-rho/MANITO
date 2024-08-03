package com.manito.community.controller;

import com.manito.community.domain.Post;
import com.manito.community.domain.Reply;
import com.manito.community.service.CommunityService;
import com.manito.community.service.ReplyService;
import com.manito.user.dto.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;
    @Autowired
    ReplyService replyService;
    @Autowired
    HttpSession session;

    @GetMapping
    public String communityBoard(Model model) throws Exception {
        Member member = (Member) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }

        System.out.println("GET post list");
        model.addAttribute("posts", communityService.getAllPosts());
        return "community/board";
    }

    @GetMapping("/create")
    public String communityCreate() {
        System.out.println("POST post form");
        return "community/post-form";
    }

    @PostMapping("/create")
    public String createPost(Post post, Model model) throws Exception {
        try {
            Member loggedInUser = (Member) session.getAttribute("user");
            if (loggedInUser == null) {
                model.addAttribute("error", "로그인이 필요합니다.");
                return "redirect:/login";
            }
            post.setUserId(loggedInUser.getUid());

            MultipartFile postImage = post.getPostImage();
            if (postImage != null && !postImage.isEmpty()) {
                try {
                    // MultipartFile을 byte[]로 변환
                    byte[] imageBytes = postImage.getBytes();
                    post.setImage(imageBytes);
                    System.out.println("POST post image");
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "파일 업로드 오류가 발생했습니다.");
                    return "redirect:/community/post/create";
                }
            }

//            System.out.println(post);
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


    // 상세 페이지 수정 / 삭제
    // 수정
    @GetMapping("/post/update")
    public String getUpdatePost(@RequestParam("pid") int postId,
                         Model model) throws Exception {
        System.out.println(postId);
        Post post = communityService.getPostById(postId);

        Member loggedInUser = (Member) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        if (post == null) {
            model.addAttribute("error", "게시물이 존재하지 않습니다.");
            return "redirect:/community";
        }

        if (loggedInUser.getUid() != post.getUserId()) {
            model.addAttribute("error", "권한이 없습니다.");
            return "redirect:/community/post/" + postId;
        }

        try {
            model.addAttribute("post", communityService.getPostById(postId));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "/community/post-form";
    }

    @PostMapping("/post/update")
    public String updatePost(Post post, Model model) throws Exception {
        try {
            MultipartFile postImage = post.getPostImage();
            if (postImage != null && !postImage.isEmpty()) {
                try {
                    byte[] imageBytes = postImage.getBytes();
                    post.setImage(imageBytes);
                    System.out.println("Update post image");
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "파일 업로드 오류가 발생했습니다.");
                    return "redirect:/community/post/update";
                }
            }
            communityService.updatePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/community/post/" + post.getPid();
    }

    // 삭제
    @GetMapping("/post/delete")
    public String deletePost(@RequestParam("pid") int postId, Model model) throws Exception {
        try {
            Member user = (Member) session.getAttribute("user");
            Post post = communityService.getPostById(postId);

            if (user == null || post == null || user.getUid() != post.getUserId()) {
                model.addAttribute("error", "삭제 권한이 없습니다.");
                return "redirect:/community/post/" + postId;
            }

            communityService.deletePost(postId);
            System.out.println("Successfully DELETE community post !!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/community";
    }

    // 댓글

    // 생성
    @PostMapping("/post/{pid}/reply")
    public String createReply(@PathVariable("pid") int postId, @RequestParam("msg") String msg, Model model) throws Exception {
//        System.out.println(postId);
        Reply reply = new Reply();
        Member loggedInUser = (Member) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        reply.setUid(loggedInUser.getUid());
        reply.setPid(postId);
        reply.setMsg(msg);

        System.out.println(reply);

        replyService.createReply(reply);

        return "redirect:/community/post/" + postId; // 댓글 추가 후 게시물 상세 페이지로 리디렉션
    }

    // 삭제
    @GetMapping("/post/reply/delete")
    public String deleteReply(@RequestParam("pid") int postId, @RequestParam("rid") int replyId, Model model) throws Exception {
        try {
            Member user = (Member) session.getAttribute("user");
            Reply reply = replyService.getReplyById(replyId);
            System.out.println(reply);

            if (user == null || reply == null || user.getUid() != reply.getUid()) {
                model.addAttribute("error", "삭제 권한이 없습니다.");
                return "redirect:/community/post/" + postId;
            }

//            System.out.println(postId  +" + "+ replyId);
            replyService.deleteReply(postId, replyId);
            System.out.println("Successfully DELETE post reply !!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/community/post/" + postId;
    }

}
