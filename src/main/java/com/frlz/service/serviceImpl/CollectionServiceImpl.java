package com.frlz.service.serviceImpl;

import com.frlz.mapper.BlogMapper;
import com.frlz.mapper.CollectionMapper;
import com.frlz.mapper.UserMapper;
import com.frlz.pojo.Blog;
import com.frlz.pojo.Collection;
import com.frlz.pojo.User;
import com.frlz.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    private CollectionMapper collectionMapper;
    private UserMapper userMapper;
    private BlogMapper blogMapper;
    @Autowired
    public CollectionServiceImpl(CollectionMapper collectionMapper,UserMapper userMapper,BlogMapper blogMapper){
        this.collectionMapper = collectionMapper;
        this.userMapper = userMapper;
        this.blogMapper = blogMapper;
    }

    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insertIntoCollection(collection.getUid(),collection.getBlogId());
    }

    @Override
    public List<Blog> getCollectBlog(String uid) {
        List<String> blogIdList = collectionMapper.selectFromCollectionByUid(uid);
        List<Blog> blogList = new ArrayList<>();
        for (String str : blogIdList){
            blogList.add(blogMapper.selectBlogByBlogId(str));
        }
        return blogList;
    }

    @Override
    public List<User> getCollectUser(String blogId) {
        List<String> uidList = collectionMapper.selectFromCollectionByBlogId(blogId);
        List<User> userList = new ArrayList<>();
        for (String str : uidList){
            userList.add(userMapper.selectByUid(str));
        }
        return userList;
    }
}
