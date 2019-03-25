package com.frlz.service.serviceImpl;

import com.frlz.mapper.BlogMapper;
import com.frlz.pojo.Blog;
import com.frlz.service.BlogService;
import com.frlz.util.DateTime;
import com.frlz.util.PageBean;
import com.frlz.utilPojo.UitlBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Transactional
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper){
        this.blogMapper = blogMapper;
    }


    @Override
    public HashMap<String,Object> findBlog(Blog blog)  {
        HashMap<String,Object> map = new HashMap<>();
        Blog finBlog = blogMapper.findBlog(blog);
        map.put("message", finBlog.getMessage());
        map.put("readNumber", finBlog.getReadNumber());
        map.put("commentsNumber", finBlog.getCommentsNumber());
        map.put("title", finBlog.getTitle());
        map.put("forwordNumber", finBlog.getForwordNumber());
        map.put("blogId", finBlog.getBlogId());
        map.put("summary", finBlog.getSummary());
        map.put("uid", finBlog.getUid());
        return map;
    }

    @Override
    public Blog getBlog(Blog blog) {
        return blogMapper.findBlog(blog);
    }

    @Override
    public HashMap<String, Object> searchBlog(int pageCode,String uid,int choice) {
        HashMap<String,Object> map = new HashMap<>();
        Map<String,Object> conditions = new HashMap<String,Object>();
        if(uid.trim().length() > 0 || uid != null) {
            conditions.put("uid",uid);//把uid放入map集合中
        }
        PageBean pb = new PageBean();
        int allCount = blogMapper.findAllCountLike(conditions);//查询数据总数
        pb.setAllCount(allCount);//把数据总数放入分页类
        pb.setPageSize(12);//把页面放入分页类
        if(pageCode > pb.getAllPages()) {//判断页码
            pageCode = pb.getAllPages();
        }
        pb.setPageCode(pageCode);
        conditions.put("pageSize", 12);
        conditions.put("pageCode", pageCode);
        List<UitlBlog> blogs = null;
        if (choice == 1){
            blogs = blogMapper.find(conditions);
        }else if (choice == 2){
            blogs = blogMapper.findChoice(conditions);
        }else {
            map.put("result","false");
            return map;
        }
        map.put("blogs", blogs);
        map.put("pageCode", pageCode);
        return map;
    }

    @Override
    public int selectBlogCountByDateAndUid(String date, String uid) {
        return blogMapper.selectBlogCountByDateAndUid(date,uid);
    }

    @Override
    public int insertBlog(Blog blog, String uid)  {
        Date date = null;
        try {
            date = DateTime.getDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String format2 = sdf2.format(date);//创建当前时间以yyyy-MM-dd格式
        int count = blogMapper.selectBlogCountByDateAndUid(format2,uid);
        blog.setTime(date);
        blog.setLikes(0);
        blog.setWeight(50);
        blog.setCommentsNumber(0);
        blog.setForwordNumber(0);
        blog.setReadNumber(0);
        blog.setUid(uid);
        blogMapper.insertBlog(blog);
        return count;
    }

    @Override
    public void updateLikesCount(String blogId) {
        blogMapper.updateLikesCount(blogId);
    }

    @Override
    public List<Blog> selectFiftyBlog(int a) {
        return blogMapper.selectFiftyBlog(a);
    }

    @Override
    public void updateBlogByBlogId(String blogId,int choice) {
        if (choice==1){//更新点赞数量
            blogMapper.updateLikesCount(blogId);
        }
        if (choice==2){//更新评论数量
            blogMapper.updateCommentsNumberByBlogId(blogId);
        }
        if (choice==3){//更新转发数量
            blogMapper.updateForwordNumberByBlogId(blogId);
        }
        if (choice==4){//更新阅读数
            blogMapper.updateReadNumberByBlogId(blogId);
        }
        if (choice==5){//取消点赞
            blogMapper.updateReduceLikesCount(blogId);
        }
    }

    @Override
    public void deleteBlog(String blogId) {
        blogMapper.deleteBlog(blogId);
    }

    @Override
    public List<Blog> selectBlogByMonth(String date) {
        return blogMapper.selectBlogByMonth(date);
    }
}
