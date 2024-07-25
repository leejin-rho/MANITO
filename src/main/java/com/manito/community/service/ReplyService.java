package com.manito.community.service;

import com.manito.community.domain.Reply;

import java.util.List;

public interface ReplyService {
    public int createReply(Reply reply) throws Exception;
//    public int updateReply(Reply reply) throws Exception;
    public int deleteReply(int rid) throws Exception;

    public Reply getReplyById(int rid) throws Exception;
    public List<Reply> getAllRepliesByPostId(int pid) throws Exception;

}
