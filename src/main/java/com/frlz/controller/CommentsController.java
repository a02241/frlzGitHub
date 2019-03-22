package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.Comments;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.CommentsService;
import com.frlz.service.UserService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RestController
public class CommentsController {

    private final CommentsService commentsService;
    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public CommentsController(CommentsService commentsService,BlogService blogService,UserService userService){
        this.commentsService = commentsService;
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("findBlog")
    /**
     * 展示评论信息
     * @title findBlog
     * @author cz
     * @date 2019/3/2 10:33
     * @param blog
     * @param pageCode
     * @param username
     * @param blogId
     * @Description: TODO 必填参数:blogId,username,pageCode(默认为1)
     *                  返回值username,
     *                  message,
     *                  commentsNumber,
     *                  title,
     *                  forwordNumber,
     *                  blogId,
     *                  summary,
     *                  uid,
     *                  pageBean(分页类,内datas的List集合-->>评论的集合信息)
     *                  result评论总数量
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throws
     */

    public R<HashMap<String,Object>> findBlog(Blog blog, @RequestParam(defaultValue="1") int pageCode, String username, String blogId) {
        Map<String,Object> conditions = new HashMap<String,Object>();
        HashMap<String,Object> map;
        if(blogId.trim().length() > 0 || blogId != null) {
            conditions.put("blogId",blogId);//把blogId放入map集合中
            int count = commentsService.findCommentsByBlogId(conditions);
            map = blogService.findBlog(blog);
            map.put("result",count);
        }else {
            return R.isFail().msg("blogId为空");
        }
        map.put("comments", commentsService.findComments(conditions, 12, pageCode));//conditions-->>map存放数据,pageCode-->>分页条数,从第几个开始
        map.put("username", username);
        return R.isOk().data(map);
    }


    @Transactional
    @PostMapping("saveComment")
    /**
     * 保存评论信息
     * @title saveComment
     * @author cz
     * @date 2019/3/2 10:47
     * @param content
     * @param comments
     * @param model
     * @param blogId
     * @Description: TODO 必填参数comments,
     *                  blogId,
     *                  username
     *                  返回success则为注册成功
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throws
     */

    public R<HashMap<String,Object>> saveComment(Comments comments) {
        String format = DateTime.getTimeByDateToString(new Date());//创建当前时间以yyyy-MM-dd格式
        int count = commentsService.selectCommentTimeCountByTime(format,comments.getUsername());//返回前一次当天写博客的次数
        if(count < 3){//回帖小于三次加8经验，超过不加
            User user = userService.selectUserByUsername(comments.getUsername());
            if (user.getExperience()<0){//0级不给经验
                return R.isOk().data("当前等级为0级,答题后可增加经验");
            }else {
                user.setExperience(user.getExperience() + 5);//发帖加8经验
                userService.updateUser(user);//写入数据库
            }
        }
        blogService.updateBlogByBlogId(comments.getBlogId(),2);//评论数+1
        commentsService.saveComment(comments);
        return R.isOk().data("success");
    }

    @PostMapping("/deleteComment")
    /**
     * 删除评论信息
     * @title deleteComment
     * @author cz
     * @date 2019/3/2 10:50
     * @param comments
     * @Description: TODO 删除信息,必填字段cId删除成功返回result为success
     * @return java.lang.String
     * @throws
     */

    public R<HashMap<String,Object>> deleteComment(Comments comments){
        commentsService.deleteComment(comments.getcId());
        return R.isOk().data("success");
    }
}
