package com.frlz.service;

import com.frlz.pojo.CheckLike;

public interface CheckLikeService {

    public void insertIntoCheckLike(CheckLike checkLike);

    public CheckLike selectFromCheckLike(String blogId,String uid);

    public void deleteFromCheckLike(int likeId);
}
