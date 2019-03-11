package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.Comments;
import com.frlz.service.BlogService;
import com.frlz.service.CommentsService;
import com.frlz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("findBlog")
    /**
     * 展示评论信息
     * @title findBlog
     * @author cz
     * @date 2019/3/2 10:33
     * @param blog
     * @param pageCode
     * @param username
     * @param blogId
     * @Description: 必填参数:blogId,username,pageCode(默认为1)
     *              返回值username,message,readNumber,commentsNumber,title,forwordNumber,blogId,summary,uid
     *              pageBean(分页类,内datas的List集合-->>评论的集合信息)
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throws
     */

    public HashMap<String,Object> findBlog(Blog blog, @RequestParam(defaultValue="1") int pageCode, String username, String blogId) throws Exception {
        Map<String,Object> conditions = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<>();
        if(blogId.trim().length()>0||blogId==null) {
            conditions.put("blogId",blogId);//把blogId放入map集合中
        }
        PageBean pb = commentsService.findComments(conditions, 12*pageCode, 12*pageCode-11);//conditions-->>map存放数据,pageCode-->>分页条数,从第几个开始
        map.put("pageBean", pb);
        Blog finBlog = blogService.finBlog(blog);//根据条件查询博客信息
        map.put("username", username);
        map.put("message", finBlog.getMessage());
        map.put("readNumber", finBlog.getReadNumber());
        map.put("commentsNumber", finBlog.getCommentsNumber());
        map.put("title", finBlog.getTitle());
        map.put("forwordNumber", finBlog.getForwordNumber());
        map.put("blogId", blogId);
        map.put("summary", finBlog.getSummary());
        map.put("uid", finBlog.getUid());
        return map;
    }

    @RequestMapping("saveComment")
    /**
     * 保存评论信息
     * @title saveComment
     * @author cz
     * @date 2019/3/2 10:47
     * @param content
     * @param comments
     * @param model
     * @param blogId
     * @Description: 必填参数comments,blogId,username
     *              返回success则为注册成功
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throws
     */

    public HashMap<String,Object> saveComment(String content,Comments comments,ModelMap model,String blogId) throws Exception {
        commentsService.saveComment(comments);
        HashMap<String,Object> map=new HashMap<>();
        map.put("result","success");
        return map;
    }

    @RequestMapping("/deleteComment")
    /**
     * 删除评论信息
     * @title deleteComment
     * @author cz
     * @date 2019/3/2 10:50
     * @param comments
     * @Description: 删除信息,必填字段cId
     *              删除成功返回result为success
     * @return java.lang.String
     * @throws
     */

    public HashMap<String,Object> deleteComment(Comments comments){
        commentsService.deleteComment(comments.getcId());
        HashMap<String,Object> map=new HashMap<>();
        map.put("result","success");
        return map;
    }

}
