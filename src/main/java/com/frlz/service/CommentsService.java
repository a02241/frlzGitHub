package com.frlz.service;

import com.frlz.pojo.Comments;
import com.frlz.util.PageBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommentsService {

    public List<Comments> findComments(Map<String, Object> conditions , int pageSize, int pageCode) throws Exception;

    public int findCommentsByBlogId(Map<String, Object> conditions) throws Exception;

    public void saveComment(Comments comments)throws Exception;

    public int selectCommentTimeCountByTime(String date,String username) throws Exception;

    public void deleteComment(String cId) throws Exception;
}
