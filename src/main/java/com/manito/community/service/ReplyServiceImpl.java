package com.manito.community.service;

import com.manito.community.dao.ReplyDao;
import com.manito.community.domain.Reply;
import com.manito.user.dao.MemberDao;
import com.manito.user.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private MemberDao memberDao;

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

    @Override
    public int getReplyCountForPost(int pid) throws Exception{
        return replyDao.countRepliesByPostId(pid);
    }

    public List<Reply> getRepliesByManitoId(int postId, int postAuthorUid) throws SQLException {
        List<Reply> replies = replyDao.selectAllRepliesByPostId(postId);
        Member postAuthor = memberDao.selectByUserId(postAuthorUid);
        for (Reply reply : replies) {
            Member replier = memberDao.selectByUserId(reply.getUid());
            if (replier != null && replier.getUsername().equals(postAuthor.getManito_name())) {
                reply.setAuthor("마니또");
            } else if (replier.getUsername().equals(postAuthor.getUsername())) {
                reply.setAuthor("글쓴이");
            }
        }
        return replies;
    }


}
