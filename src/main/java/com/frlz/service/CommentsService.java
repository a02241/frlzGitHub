package com.frlz.service;

import com.frlz.pojo.Comments;
import com.frlz.util.PageBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommentsService {

    public List<Comments> findComments(Map<String, Object> conditions , int pageSize, int pageCode);

    public int findCommentsByBlogId(Map<String, Object> conditions);

    public void saveComment(Comments comments);

    public int selectCommentTimeCountByTime(String date,String username);

    public void deleteComment(String cId);
}
