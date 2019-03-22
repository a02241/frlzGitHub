package com.frlz.service.serviceImpl;

import com.frlz.mapper.CommentsMapper;
import com.frlz.pojo.Comments;
import com.frlz.service.CommentsService;
import com.frlz.util.DateTime;
import com.frlz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsMapper commentsMapper;

    @Autowired
    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public List<Comments> findComments(Map<String, Object> conditions, int pageSize, int pageCode)  {
        PageBean pb = new PageBean();
        int allCount = commentsMapper.findAllCountComments(conditions);//查询数据总数
        pb.setAllCount(allCount);//把数据总数放入分页类
        pb.setPageSize(pageSize);//把页面放入分页类
        if(pageCode > pb.getAllPages()) {//判断页码
            pageCode = pb.getAllPages();
        }
        pb.setPageCode(pageCode);
        conditions.put("pageSize", pageSize);
        conditions.put("pageCode", pageCode);
        List<Comments> list = commentsMapper.findComments(conditions);
        return list;
    }

    @Override
    public int findCommentsByBlogId(Map<String, Object> conditions)  {
        return commentsMapper.findAllCountComments(conditions);
    }

    @Override
    public void saveComment(Comments comments)  {
        Date time = DateTime.getDate();
        comments.setCommentTime(time);
        commentsMapper.saveComment(comments);
    }

    @Override
    public int selectCommentTimeCountByTime(String date,String username) {
        return commentsMapper.selectCommentTimeCountByTime(date,username);
    }

    @Override
    public void deleteComment(String cId) {
        commentsMapper.deleteComment(cId);
    }
}
