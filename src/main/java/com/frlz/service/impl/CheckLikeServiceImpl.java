package com.frlz.service.impl;

import com.frlz.mapper.CheckLikeMapper;
import com.frlz.pojo.CheckLike;
import com.frlz.service.CheckLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author cz
 */
@Service
public class CheckLikeServiceImpl implements CheckLikeService {

    private final CheckLikeMapper checkLikeMapper;

    @Autowired
    public CheckLikeServiceImpl(CheckLikeMapper checkLikeMapper){
        this.checkLikeMapper = checkLikeMapper;
    }

    @Override
    public void insertIntoCheckLike(String blogId, String uid) {

        CheckLike checkLike = new CheckLike();
        checkLike.setBlogId(blogId);
        checkLike.setUid(uid);
        checkLike.setState(true);
        checkLikeMapper.insertIntoCheckLike(checkLike);
    }

    @Override
    public CheckLike selectFromCheckLike(String blogId, String uid) {
        return checkLikeMapper.selectFromCheckLike(blogId,uid);
    }

    @Override
    public void deleteFromCheckLike(int likeId) {
        checkLikeMapper.deleteFromCheckLike(likeId);
    }
}
