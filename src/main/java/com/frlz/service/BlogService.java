package com.frlz.service;

import com.frlz.pojo.Blog;
import com.frlz.util.PageBean;

import java.util.Date;
import java.util.Map;

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
     * @title findBy
     * @author cz
     * @date 2019/3/1 10:49
     * @param conditions
     * @Description:
     * @return PageBean
     * @throws Exception
     */
    public PageBean findBy(Map<String, Object> conditions , int pageSize, int pageCode) throws Exception;

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
    public Blog findBlog(Blog blog)throws Exception;

    public Date selectLatestBlogTime(String uid);

    public int selectBlogCountByDateAndUid(String date,String uid);

    public void insertBlog(Blog blog)throws Exception;

    public void updateLikesCount(int likes,String blogId);

    public void updateDislikeCount(int dislike,String blogId);
}
