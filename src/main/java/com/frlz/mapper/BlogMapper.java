package com.frlz.mapper;

import com.frlz.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BlogMapper {

    @Select("<script> " +
            "select count(*) from blog" +
            " <where> " +
            " <if test=\"uid!=null and uid!=''\"> AND uid=#{uid}</if> " +
            " </where> " +
            " </script> ")
    public int findAllCountLike(Map<String,Object> map);

    @Select("<script> " +
            "select blogId,time,likes,title,summary,message,commentsNumber,forwordNumber,readNumber from blog where uid=#{uid}" +
            "order by time desc limit ${(pageCode-1)*pageSize},${pageSize} "+
            " </script> ")
    /*@Results({
            @Result(property = "user", column = "uid",
                    many = @Many(select = "com.frlz.mapper.UserMapper.searchUserById"))
    })*/
    public List<Blog> find(Map<String,Object> map);

    @Select("<script> " +
            "select blogId,time,likes,title,summary,message,commentsNumber,forwordNumber,readNumber from blog" +
            "order by time desc limit ${(pageCode-1)*pageSize},${pageSize} "+
            " </script> ")
    public List<Blog> findChoice(Map<String,Object> map);
    /** 
     * @Title: finBlog 
     * @Description: TODO(查询博客信息) 
     * @param @param blog
     * @param @return    入参
     * @return Blog    返回类型
     * @author cz 
     * @throws
     * @date 2019年2月27日 上午10:33:14 
     * @version V1.0   
     */
    @Select("<script> " +
            "select * from blog" +
            " <where> " +
            " <if test=\"blogId!=null and blogId!=''\"> AND blogId = '${blogId}'</if> " +
            " </where> " +
            " </script> ")
    public Blog findBlog(Blog blog);

    @Select("select * from blog where DATE_FORMAT(time, '%Y-%m-%d') = #{date}")
    List<Blog> selectBlogByDate(String date);

    @Select("select count(*) from blog where DATE_FORMAT(time, '%Y-%m-%d') = #{date} and uid = #{uid}")
    int selectBlogCountByDateAndUid(String date,String uid);

    @Select("select max(time) from blog where uid = #{uid}")
    Date selectLatestBlogTime(String uid);

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

    @Update("update blog set fansNumber = fansNumber + 1 where blogId = #{blogId}")
    void updateFansNumberByBlogId(String blogId);

    @Delete("delete from blog where blogId = #{blogId} ")
    void deleteBlog(String blogId);

    @Select("select * from blog where DATE_FORMAT(time, '%Y-%m') = #{date}")
    List<Blog> selectBlogByMonth(String date);

}
