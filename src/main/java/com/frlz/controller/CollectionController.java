package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.Collection;
import com.frlz.pojo.User;
import com.frlz.service.CollectionService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {

    private CollectionService collectionService;
    @Autowired
    public CollectionController(CollectionService collectionService){
        this.collectionService = collectionService;
    }

    @PostMapping("/addCollection")
    public R addCollection(Collection collection){
        collectionService.addCollection(collection);
        return R.isOk();
    }

    @PostMapping("/getCollectBlog")
    public R<Blog> getCollectBlog(String uid){
        return R.isOk().data(collectionService.getCollectBlog(uid));//根据uid获取用户已收藏的博客,最新的收藏在第一位
    }

    @PostMapping("/getCollectUser")
    public R<User> getCollectUser(String blogId){
        return R.isOk().data(collectionService.getCollectUser(blogId));//根据blogId获取收藏该博客的所有用户，最先收藏的用户在第一位
    }

}
