package com.manito.community.service;

import com.manito.community.domain.Post;

import java.util.List;

public interface CommunityService {
    public int createPost(Post post) throws Exception;
    public int updatePost(Post post) throws Exception;
    public int deletePost(int pid) throws Exception;

    public Post getPostById(int pid) throws Exception;
    public List<Post> getAllPosts() throws Exception;
}
