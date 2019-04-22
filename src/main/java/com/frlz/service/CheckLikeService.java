package com.frlz.service;

import com.frlz.pojo.CheckLike;
/**
 * @author cz
 */
public interface CheckLikeService {

    public void insertIntoCheckLike(String blogId, String uid);

    public CheckLike selectFromCheckLike(String blogId,String uid);

    public void deleteFromCheckLike(int likeId);
}
