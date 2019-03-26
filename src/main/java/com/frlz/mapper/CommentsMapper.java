package com.frlz.mapper;

import com.frlz.pojo.Comments;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CommentsMapper {

    /** 
     * @Title: findAllCountComments 
     * @Description: TODO(查询评论页面总数量) 
     * @param @param map
     * @param @return    入参
     * @return int    返回类型
     * @author cz 
     * @throws
     * @date 2019年2月27日 上午10:33:53 
     * @version V1.0   
     */
    @Select("<script> " +
            "select count(*) from comments" +
            " <where> " +
            " <if test=\"blogId!=null and blogId!=''\"> AND blogId = #{blogId}</if> " +
            " </where> " +
            " </script> ")
    int findAllCountComments(Map<String,Object> map);

    /** 
     * @Title: findComments 
     * @Description: TODO(根据博客id查询评论信息) 
     * @param @param map
     * @param @return
     * @param @    入参
     * @return List<Comments>    返回类型
     * @author cz 
     * @throws
     * @date 2019年2月27日 上午10:34:22 
     * @version V1.0   
     */
    @Select("select * from comments where blogId = #{blogId}" +
            " order by commentTime" +
            " limit ${(pageCode-1)*pageSize},${pageSize}")
    List<Comments> findComments(Map<String,Object> map);

    @Select("select count(*) from comments where DATE_FORMAT(commentTime, '%Y-%m-%d') = #{date} and username = #{username}")
    int selectCommentTimeCountByTime(String date,String username);

    /** 
     * @Title: saveComment 
     * @Description: TODO(保存评论信息) 
     * @param @param comments
     * @param @    入参
     * @return void    返回类型
     * @author cz 
     * @throws
     * @date 2019年2月27日 上午10:35:05 
     * @version V1.0   
     */
    @SelectKey(keyProperty = "cId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "cId", useGeneratedKeys = true)
    @Insert("insert into comments(blogId, comments,cId,username,commentTime)" +
            " values( #{blogId},#{comments}, #{cId}, #{username}, #{commentTime})")
    void saveComment(Comments comments);


    @Delete("delete from comments where cId = #{cId}")
    void deleteComment(String cId);
}
