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

    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper){
        this.blogMapper = blogMapper;
    }

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

    @Override
    public List<Blog> selectFiftyBlog(int a) {
        return blogMapper.selectFiftyBlog(a);
    }

    @Override
    public void updateBlogByBlogId(String blogId , int readNumber , int commentsNumber , int forwordNumber) {
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog = blogMapper.findBlog(blog);
        if (readNumber != 0){
            int newReadNumber = blog.getReadNumber() + 1;
            blogMapper.updateReadNumberByBlogId(newReadNumber,blogId);
        }
        if (commentsNumber != 0){
            int newCommentsNumber = blog.getCommentsNumber() + 1;
            blogMapper.updateCommentsNumberByBlogId(newCommentsNumber,blogId);
        }
        if (forwordNumber != 0){
            int newFordNumber = blog.getForwordNumber() + 1;
            blogMapper.updateForwordNumberByBlogId(newFordNumber,blogId);
        }
    }

    @Override
    public void deleteBlog(String blogId) {
        blogMapper.deleteBlog(blogId);
    }

    @Override
    public List<Blog> selectBlogByDate(String date) {
        return blogMapper.selectBlogByDate(date);
    }

    @Override
    public List<Blog> selectBlogByMonth(String date) {
        return blogMapper.selectBlogByMonth(date);
    }
}
