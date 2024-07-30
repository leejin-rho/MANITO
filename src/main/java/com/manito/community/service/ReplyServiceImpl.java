package com.manito.community.service;

import com.manito.community.dao.ReplyDao;
import com.manito.community.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public int createReply(Reply reply) throws Exception {
        reply.setRegdate(new Date());
        System.out.println(reply);
        return replyDao.insertReply(reply);
    }

    @Override
    public int deleteReply(int pid, int rid) throws Exception {
        return replyDao.deleteReply(pid, rid);
    }

    @Override
    public Reply getReplyById(int rid) throws Exception {
        return replyDao.selectReplyById(rid);
    }

    @Override
    public List<Reply> getAllRepliesByPostId(int pid) throws Exception {
        return replyDao.selectAllRepliesByPostId(pid);
    }

}
