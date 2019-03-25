package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.service.BlogService;
import com.frlz.service.CheckLikeService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class CheckLikeController {

    private final CheckLikeService checkLikeService;
    private final BlogService blogService;

    @Autowired
    public CheckLikeController(CheckLikeService checkLikeService,BlogService blogService){
        this.checkLikeService = checkLikeService;
        this.blogService = blogService;
    }

    @Transactional
    @PostMapping("/clickLike")
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

    public R<String> clickLike(Blog blog, String uid){
        if (null == checkLikeService.selectFromCheckLike(blog.getBlogId(),uid)) {
            Blog blog2 = blogService.getBlog(blog);
            blogService.updateLikesCount(blog.getBlogId());
            checkLikeService.insertIntoCheckLike(blog,uid);
            return R.isOk().msg("+1");
        }else {
            checkLikeService.deleteFromCheckLike(checkLikeService.selectFromCheckLike(blog.getBlogId(),uid).getLikeId());
            Blog blog2 = blogService.getBlog(blog);
            blogService.updateLikesCount(blog.getBlogId());
            return R.isOk().msg("-1");
        }
    }
}
