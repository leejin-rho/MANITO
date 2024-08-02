package com.manito.community.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Post {
    private int pid;
    private String postTitle;
    private String postContent;
    private int likes;
    private byte[] image;
    private Date regdate;
    private int userId;

    private MultipartFile postImage;
    private int replyNum = 0;

    public Post() {

    }

    public Post(int pid, String postTitle, String postContent, int likes, List<Reply> replyList, byte[] image, Date regdate, int userId, int replyNum, MultipartFile postImage) {
        this.pid = pid;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.likes = likes;
        this.image = image;
        this.regdate = regdate;
        this.userId = userId;
        this.replyNum = replyNum;
        this.postImage = postImage;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public MultipartFile getPostImage() {
        return postImage;
    }

    public void setPostImage(MultipartFile postImage) {
        this.postImage = postImage;
    }

    @Override
    public String toString() {
        return "Post{" +
                "pid=" + pid +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", likes=" + likes +
                ", image=" + Arrays.toString(image) +
                ", regdate=" + regdate +
                ", userId=" + userId +
                ", replyNum=" + replyNum +
                ", postImage=" + postImage +
                '}';
    }
}
