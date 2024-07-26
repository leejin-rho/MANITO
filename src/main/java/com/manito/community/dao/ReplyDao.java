package com.manito.community.dao;

import com.manito.community.domain.Reply;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ReplyDao {
    @Insert("insert into reply (uid, pid, msg, regdate) values (#{uid}, #{pid}, #{msg}, #{regtime})")
    int insertReply(Reply reply) throws SQLException;

    @Update("update reply set msg = #{msg} where rid = #{rid}")
    int updateReply(Reply reply) throws SQLException;

    @Delete("delete from reply where pid = #{pid} and rid = #{rid}")
    int deleteReply(int pid, int rid) throws SQLException;

    @Select("select rid, uid, pid, msg, regdate from reply where rid = #{rid}")
    Reply selectReplyById(int rid) throws SQLException;

    @Select("select rid, uid, pid, msg, regdate from reply where pid = #{pid} order by regdate")
    List<Reply> selectAllRepliesByPostId(int pid) throws SQLException;

    @Select("select rid, uid, pid, msg, regdate from reply where uid = #{uid} order by regdate")
    List<Reply> selectAllRepliesByUserId(int uid) throws SQLException;
}
