package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.CheckLike;
import com.frlz.service.BlogService;
import com.frlz.service.CheckLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckLikeController {
    @Autowired
    private CheckLikeService checkLikeService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/clickLike")
    public String clickLike(Blog blog, String uid) throws Exception{
        if (null == checkLikeService.selectFromCheckLike(blog.getBlogId(),uid)) {
            Blog blog2 = blogService.findBlog(blog);
            blogService.updateLikesCount(blog2.getLikes() + 1,blog.getBlogId());
            CheckLike checkLike = new CheckLike();
            checkLike.setBlogId(blog.getBlogId());
            checkLike.setUid(uid);
            checkLike.setState(true);
            checkLikeService.insertIntoCheckLike(checkLike);
            return "+1";
        }else {
            checkLikeService.deleteFromCheckLike(checkLikeService.selectFromCheckLike(blog.getBlogId(),uid).getLikeId());
            Blog blog2 = blogService.findBlog(blog);
            blogService.updateLikesCount(blog2.getLikes() - 1,blog.getBlogId());
            return "-1";
        }
    }
}
