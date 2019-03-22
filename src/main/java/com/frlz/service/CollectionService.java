package com.frlz.service;

import com.frlz.pojo.Blog;
import com.frlz.pojo.Collection;
import com.frlz.pojo.User;

import java.util.List;

public interface CollectionService {

    void addCollection(Collection collection);

    List<Blog> getCollectBlog(String uid);

    List<User> getCollectUser(String blogId);
}
