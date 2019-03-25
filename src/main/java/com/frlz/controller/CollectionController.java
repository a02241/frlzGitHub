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
    /**
     *
     * @title addCollection
     * @create by: cz
     * @description: TODO 必填参数：collection只需传uid，blogId这两个值
     * @create time: 2019/3/25 10:13
     * @Param: collection
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R addCollection(Collection collection){//collection只需传uid，blogId这两个值
        collectionService.addCollection(collection);
        return R.isOk();
    }

    @PostMapping("/getCollectBlog")
    /**
     *
     * @title getCollectBlog
     * @create by: cz
     * @description: TODO 必填参数：uid 返回博客信息
     * @create time: 2019/3/25 10:15
     * @Param: uid
     * @throws
     * @return com.frlz.util.R<com.frlz.pojo.Blog>
     * @version V1.0
     */

    public R<Blog> getCollectBlog(String uid){
        return R.isOk().data(collectionService.getCollectBlog(uid));//根据uid获取用户已收藏的博客,最新的收藏在第一位
    }

    @PostMapping("/getCollectUser")
    /**
     * 
     * @title getCollectUser
     * @create by: cz
     * @description: TODO 必填参数：blogId 返回用户信息
     * @create time: 2019/3/25 10:16
     * @Param: blogId
     * @throws 
     * @return com.frlz.util.R<com.frlz.pojo.User>
     * @version V1.0
     */
    
    public R<User> getCollectUser(String blogId){
        return R.isOk().data(collectionService.getCollectUser(blogId));//根据blogId获取收藏该博客的所有用户，最先收藏的用户在第一位
    }

}
