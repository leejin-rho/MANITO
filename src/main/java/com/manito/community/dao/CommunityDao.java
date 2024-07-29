package com.manito.community.dao;
import com.manito.community.domain.Post;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommunityDao {
    @Insert("insert community (pid, post_title, post_content, likes, image, regdate, user_id) values (#{pid}, #{postTitle}, #{postContent}, #{likes}, #{image}, #{regdate}, #{userId})")
    int insertPost(Post post) throws SQLException;

    @Update("update community set post_title=#{postTitle}, post_content=#{postContent}, image=#{image} where pid=#{pid}")
    int updatePost(Post post) throws SQLException;

    @Update("update community set likes = likes + 1 where pid=#{pid}")
    int updateLikes(int pid) throws SQLException;

    @Delete("delete from community where pid=#{pid}")
    int deletePost(int pid) throws SQLException;

    @Select("select pid, post_title, post_content, likes, image, regdate, user_id from community where pid=#{pid}")
    @Results(id = "postResultMap", value = {
            @Result(property = "pid", column = "pid"),
            @Result(property = "postTitle", column = "post_title"),
            @Result(property = "postContent", column = "post_content"),
            @Result(property = "likes", column = "likes"),
            @Result(property = "image", column = "image"),
            @Result(property = "regdate", column = "regdate"),
            @Result(property = "userId", column = "user_id")
    })
    Post selectPostById(int pid) throws SQLException;

    @Select("select pid, post_title, post_content, likes, image, regdate, user_id from community order by regdate")
    @ResultMap("postResultMap")
    List<Post> selectAllPosts() throws SQLException;
}
