package com.manito.community.service;

import com.manito.community.domain.Reply;

import java.sql.SQLException;
import java.util.List;

public interface ReplyService {
    public int createReply(Reply reply) throws Exception;
    public int deleteReply(int pid, int rid) throws Exception;
    public Reply getReplyById(int rid) throws Exception;
    public List<Reply> getAllRepliesByPostId(int pid) throws Exception;
    public int getReplyCountForPost(int pid) throws Exception;
    public List<Reply> getRepliesByManitoId(int postId, int postAuthorUid) throws SQLException;
}
