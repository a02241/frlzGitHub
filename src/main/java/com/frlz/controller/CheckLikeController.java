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
    /**
     * 点赞
     * @title clickLike
     * @create by: cz
     * @description: TODO 必填参数blogId,uid，返回未点击喜欢返回+1，已经点击过了再次点击返回-1
     * @create time: 2019/3/13 16:14
     * @Param: blog
     * @Param: uid
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

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
