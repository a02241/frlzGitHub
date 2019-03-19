package com.frlz.service;

import com.frlz.pojo.Blog;

import java.util.HashMap;
import java.util.List;

/**
 * @Project: frlz
 * @Package com.frlz.service
 * @Description: TODO
 * @author : cz
 * @date Date : 2019-03-01 10:40
 * @version V1.0
 */

public interface BlogService {


    /** 
     * @Title: finBlog 
     * @Description: TODO(查询博客信息) 
     * @param @param blog
     * @param @return
     * @param @throws Exception    入参
     * @return Blog    返回类型
     * @author cz 
     * @throws
     * @date 2019年2月27日 上午10:52:32 
     * @version V1.0   
     */
    public HashMap<String,Object> findBlog(Blog blog)throws Exception;

    Blog getBlog(Blog blog);

    HashMap<String,Object> searchBlog(int pageCode,String uid);

    public int selectBlogCountByDateAndUid(String date,String uid);

    public int insertBlog(Blog blog, String uid)throws Exception;

    public void updateLikesCount(int likes,String blogId);

    public List<Blog> selectFiftyBlog(int a);

    public void updateBlogByBlogId(String blogId , int readNumber , int commentsNumber , int forwordNumber);

    void deleteBlog(String blogId);

    List<Blog> selectBlogByMonth(String date);
}
