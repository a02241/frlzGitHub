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

    public HashMap<String,Object> findBlog(Blog blog);

    Blog getBlog(Blog blog);

    HashMap<String,Object> searchBlog(int pageCode,String uid,int choice);

    public int selectBlogCountByDateAndUid(String date,String uid);

    public int insertBlog(Blog blog, String uid);

    public void updateLikesCount(String blogId);

    public List<Blog> selectFiftyBlog(int a);

    public void updateBlogByBlogId(String blogId,int choice);

    public void deleteBlog(String blogId);

    List<Blog> selectBlogByMonth(String date);
}
