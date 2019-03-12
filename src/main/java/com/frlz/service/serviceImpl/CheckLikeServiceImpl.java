package com.frlz.service.serviceImpl;

import com.frlz.mapper.CheckLikeMapper;
import com.frlz.pojo.CheckLike;
import com.frlz.service.CheckLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckLikeServiceImpl implements CheckLikeService {

    @Autowired
    private CheckLikeMapper checkLikeMapper;

    @Override
    public void insertIntoCheckLike(CheckLike checkLike) {
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
