package com.frlz.service.serviceImpl;

import com.frlz.mapper.CheckLikeMapper;
import com.frlz.pojo.Blog;
import com.frlz.pojo.CheckLike;
import com.frlz.service.CheckLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckLikeServiceImpl implements CheckLikeService {

    private final CheckLikeMapper checkLikeMapper;

    @Autowired
    public CheckLikeServiceImpl(CheckLikeMapper checkLikeMapper){
        this.checkLikeMapper = checkLikeMapper;
    }

    @Override
    public void insertIntoCheckLike(Blog blog, String uid) throws Exception{

        CheckLike checkLike = new CheckLike();
        checkLike.setBlogId(blog.getBlogId());
        checkLike.setUid(uid);
        checkLike.setState(true);
        checkLikeMapper.insertIntoCheckLike(checkLike);
    }

    @Override
    public CheckLike selectFromCheckLike(String blogId, String uid) throws Exception{
        return checkLikeMapper.selectFromCheckLike(blogId,uid);
    }

    @Override
    public void deleteFromCheckLike(int likeId) throws Exception{
        checkLikeMapper.deleteFromCheckLike(likeId);
    }
}
