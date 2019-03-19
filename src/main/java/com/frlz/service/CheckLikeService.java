package com.frlz.service;

import com.frlz.pojo.Blog;
import com.frlz.pojo.CheckLike;

public interface CheckLikeService {

    public void insertIntoCheckLike(Blog blog, String uid);

    public CheckLike selectFromCheckLike(String blogId,String uid);

    public void deleteFromCheckLike(int likeId);
}
