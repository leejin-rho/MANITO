package com.manito.community.domain;

import java.util.Date;
import java.util.List;

public class Post {
    private int pid;
    private String postTitle;
    private String postContent;
    private int likes;
    private List<Reply> replyList;
    private byte[] image;
    private Date regdate;
    private int userId;

    public Post() {

    }

    public Post(int pid, String postTitle, String postContent, int likes, List<Reply> replyList, byte[] image, Date regdate, int userId) {
        this.pid = pid;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.likes = likes;
        this.replyList = replyList;
        this.image = image;
        this.regdate = regdate;
        this.userId = userId;
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

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
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
}
