package com.frlz.service.serviceImpl;

import com.frlz.mapper.BlogMapper;
import com.frlz.pojo.Blog;
import com.frlz.service.BlogService;
import com.frlz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageBean findBy(Map<String, Object> conditions, int pageSize, int pageCode) throws Exception {
        PageBean pb = new PageBean();
        int allCount = blogMapper.findAllCountLike(conditions);//查询数据总数
        pb.setAllCount(allCount);//把数据总数放入分页类
        pb.setPageSize(pageSize);//把页面放入分页类
        if(pageCode > pb.getAllPages()) {//判断页码
            pageCode = pb.getAllPages();
        }
        pb.setPageCode(pageCode);


        conditions.put("pageSize", pageSize);
        conditions.put("pageCode", pageCode);
        List<Blog> list = blogMapper.find(conditions);
        pb.setDatas(list);
        return pb;
    }

    @Override
    public Blog findBlog(Blog blog) throws Exception {
        return blogMapper.findBlog(blog);
    }

    @Override
    public Date selectLatestBlogTime(String uid) {
        return blogMapper.selectLatestBlogTime(uid);
    }

    @Override
    public int selectBlogCountByDateAndUid(String date, String uid) {
        return blogMapper.selectBlogCountByDateAndUid(date,uid);
    }

    @Override
    public void insertBlog(Blog blog) throws Exception {
        blogMapper.insertBlog(blog);
    }

    @Override
    public void updateLikesCount(int likes, String blogId) {
        blogMapper.updateLikesCount(likes,blogId);
    }

    @Override
    public void updateDislikeCount(int dislike, String blogId) {
        blogMapper.updateDislikeCount(dislike,blogId);
    }
}
