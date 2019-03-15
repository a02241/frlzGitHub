package com.frlz.service.serviceImpl;

import com.frlz.mapper.CommentsMapper;
import com.frlz.pojo.Comments;
import com.frlz.service.CommentsService;
import com.frlz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public PageBean findComments(Map<String, Object> conditions, int pageSize, int pageCode) throws Exception {
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
        pb.setDatas(list);
        return pb;
    }

    @Override
    public int findCommentsByBlogId(Map<String, Object> conditions) throws Exception {
        return commentsMapper.findAllCountComments(conditions);
    }

    @Override
    public void saveComment(Comments comments) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newtime = sdf.format(date);
        Date time = sdf.parse(newtime);
        comments.setCommentTime(time);
        commentsMapper.saveComment(comments);
    }

    @Override
    public int selectCommentTimeCountByTime(String date,String username) {
        return commentsMapper.selectCommentTimeCountByTime(date,username);
    }

    @Override
    public void deleteComment(String cId){
        commentsMapper.deleteComment(cId);
    }
}
