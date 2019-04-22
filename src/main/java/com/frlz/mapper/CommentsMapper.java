package com.frlz.mapper;

import com.frlz.pojo.Comments;
import com.frlz.dto.UtilComments;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * @author cz
 */
public interface CommentsMapper {

    @Select("<script> " +
            "select count(*) from comments" +
            " <where> " +
            " <if test=\"blogId!=null and blogId!=''\"> AND blogId = #{blogId}</if> " +
            " </where> " +
            " </script> ")
    int findAllCountComments(Map<String,Object> map);



    @Select("select * from comments c where c.blogId = #{blogId}" +
            " order by c.commentTime" +
            " limit ${(pageCode-1)*pageSize},${pageSize}")
    @Results({
            @Result(property = "username", column = "uid",
                    one = @One(select = "com.frlz.mapper.UserMapper.searchUsernameById")),
            @Result(property = "icon", column = "uid",
                    one = @One(select = "com.frlz.mapper.UserMapper.searchIconById")),
            @Result(property = "utilReplys", column = "cId",
                    one = @One(select = "com.frlz.mapper.ReplysMapper.selectReplysByCId"))
    })
    List<UtilComments> findComments(Map<String,Object> map);




    @Select("select count(*) from comments where DATE_FORMAT(commentTime, '%Y-%m-%d') = #{date} and uid = #{uid}")
    int selectCommentTimeCountByTime(String date,String uid);



    @SelectKey(keyProperty = "cId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "cId", useGeneratedKeys = true)
    @Insert("insert into comments(blogId, comments,cId,uid,commentTime)" +
            " values( #{blogId},#{comments}, #{cId}, #{uid}, #{commentTime})")
    void saveComment(Comments comments);


    @Delete("delete from comments where cId = #{cId}")
    void deleteComment(String cId);
}
