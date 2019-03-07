package com.frlz.service;

import com.frlz.pojo.Comments;
import com.frlz.util.PageBean;

import java.util.Map;

public interface CommentsService {

    public PageBean findComments(Map<String, Object> conditions , int pageSize, int pageCode) throws Exception;

    public void saveComment(Comments comments)throws Exception;

    public void deleteComment(String cId);
}
