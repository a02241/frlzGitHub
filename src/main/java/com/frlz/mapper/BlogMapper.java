package com.frlz.mapper;

import com.frlz.pojo.Blog;
import com.frlz.utilPojo.UitlBlog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    @Select("<script> " +
            "select count(*) from blog" +
            " <where> " +
            " <if test=\"uid!=null and uid!=''\"> AND uid=#{uid}</if> " +
            " </where> " +
            " </script> ")
    int findAllCountLike(Map<String,Object> map);


    @Select("select u.icon , u.username , b.uid, b.blogId,b.time,b.likes,b.title,b.summary,b.message,b.commentsNumber,b.forwordNumber,b.readNumber from blog b inner join user u " +
            "where b.uid = u.uid" +
            " order by weight desc limit ${(pageCode-1)*pageSize},${pageSize} ")
   /* @Results({
            @Result(property = "UitlBlog"),
            @Result(property = "username", column = "uid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "icon", column = "uid")
    })*/
    List<UitlBlog> findChoice(Map<String,Object> map);

    @Select("select u.icon , u.username , b.uid, b.blogId,b.time,b.likes,b.title,b.summary,b.message,b.commentsNumber,b.forwordNumber,b.readNumber from blog b inner join user u " +
            "where u.uid = #{uid}" +
            " order by weight desc limit ${(pageCode-1)*pageSize},${pageSize} ")
    /*@Results({
            @Result(property = "username", column = "uid",
                    many = @Many(select = "com.frlz.mapper.UserMapper.searchUsernameById"))
    })*/
    List<UitlBlog> find(Map<String,Object> map);

    @Select("<script> " +
            "select * from blog" +
            " <where> " +
            " <if test=\"blogId!=null and blogId!=''\"> AND blogId = '${blogId}'</if> " +
            " </where> " +
            " </script> ")
    Blog findBlog(Blog blog);

    @Select("select * from blog where blogId = #{blogId}")
    Blog selectBlogByBlogId(String blogId);

    @Select("select count(*) from blog where DATE_FORMAT(time, '%Y-%m-%d') = #{date} and uid = #{uid}")
    int selectBlogCountByDateAndUid(String date,String uid);

    @Select("select * from blog order by time desc limit #{a},20")
    List<Blog> selectFiftyBlog(int a);

    @SelectKey(keyProperty = "blogId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "blogId", useGeneratedKeys = true)
    @Insert("insert into blog" +
            " values( #{blogId}," +
            "#{message}, " +
            "#{time}, " +
            "#{likes}, " +
            "#{weight}, " +
            "#{commentsNumber}, " +
            "#{forwordNumber}, " +
            "#{readNumber}, " +
            "#{uid}, " +
            "#{title}, " +
            "#{summary})")
    void insertBlog(Blog blog);

    @Update("update blog set likes = likes + 1,weight = weight + 0.1 where blogId = #{blogId}")
    void updateLikesCount(String blogId);

    @Update("update blog set likes = likes -1 ,weight = weight - 0.1 where blogId = #{blogId}")
    void updateReduceLikesCount(String blogId);

    @Update("update blog set readNumber = readNumber + 1 where blogId = #{blogId}")
    void updateReadNumberByBlogId(String blogId);

    @Update("update blog set commentsNumber = commentsNumber + 1,weight = weight + 0.5 where blogId = #{blogId}")
    void updateCommentsNumberByBlogId(String blogId);

    @Update("update blog set forwordNumber = forwordNumber + 1 ,weight = weight + 1.0 where blogId = #{blogId}")
    void updateForwordNumberByBlogId(String blogId);

    @Delete("delete from blog where blogId = #{blogId} ")
    void deleteBlog(String blogId);

    @Select("select * from blog where DATE_FORMAT(time, '%Y-%m') = #{date}")
    List<Blog> selectBlogByMonth(String date);

}
