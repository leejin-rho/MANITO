package com.manito.user.dao;

import com.manito.community.domain.Reply;
import com.manito.user.dto.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

@Mapper
public interface MemberDao {
    @Insert("insert into member ( uid, username, manito_name, login_id, password ) values ( #{uid}, #{username}, #{manito_name}, #{login_id}, #{password})")
    int signUp(Member member) throws SQLException;

    @Delete("delete from member where uid = #{uid}")
    int deleteMember(int pid, int rid) throws SQLException;

    @Select("select uid, username, manito_name, login_id, password from member where uid = #{uid}")
    Member selectByUserId(int uid) throws SQLException;

    @Select("select uid, username, manito_name, login_id, password from member where login_id = #{login_id}")
    Member selectByLoginId(String loginId) throws SQLException;

    @Select("select uid from member where username = #{name}")
    int selectIdByName(String name) throws SQLException;
}
