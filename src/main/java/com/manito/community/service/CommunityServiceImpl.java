package com.manito.community.service;

import com.manito.community.dao.CommunityDao;
import com.manito.community.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    private CommunityDao communityDao;

    @Override
    public int createPost(Post post) throws Exception{
        post.setRegdate(new Date());
        post.setUserId(2);
        return communityDao.insertPost(post);
    }

    @Override
    public int updatePost(Post post) throws Exception {
        return communityDao.updatePost(post);
    }

    @Override
    public int deletePost(int pid) throws Exception {
        return communityDao.deletePost(pid);
    }

    @Override
    public Post getPostById(int pid) throws Exception {
        return communityDao.selectPostById(pid);
    }

    @Override
    public List<Post> getAllPosts() throws Exception {
        return communityDao.selectAllPosts();
    }
}
