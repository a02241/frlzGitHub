package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.Comments;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.CommentsService;
import com.frlz.service.ReplysService;
import com.frlz.service.UserService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RestController
@Api(value="评论信息controller",tags={"评论信息操作接口"})
public class CommentsController {

    private final CommentsService commentsService;
    private final BlogService blogService;
    private final UserService userService;
    private final ReplysService replysService;

    @Autowired
    public CommentsController(CommentsService commentsService, BlogService blogService, UserService userService, ReplysService replysService){
        this.commentsService = commentsService;
        this.blogService = blogService;
        this.userService = userService;
        this.replysService = replysService;
    }

    
    /**
     * 展示评论信息
     * @title findBlog
     * @author cz
     * @date 2019/3/2 10:33
     * @param blogId
     * @param pageCode
     * @Description TODO 必填参数:blogId,pageCode(默认为1)
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
     * 
     */
    @PostMapping("findBlog")
    @ApiOperation(value="展示评论信息", notes="根据url的信息来展示评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId", value = "博客识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageCode", value = "页码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> findBlog(@RequestParam(defaultValue="1") int pageCode, String blogId) {
        Map<String,Object> conditions = new HashMap<String,Object>();
        HashMap<String,Object> map;
        if(blogId.trim().length() > 0 || blogId != null) {
            conditions.put("blogId",blogId);//把blogId放入map集合中
            int count = commentsService.findCommentsByBlogId(conditions);
            Blog blog = new Blog();
            blog.setBlogId(blogId);
            map = blogService.findBlog(blog);
            map.put("result",count);
        }else {
            return R.isFail().msg("blogId为空");
        }
        conditions.put("blogId",blogId);//把blogId放入map集合中
        List<Comments> comments =commentsService.findComments(conditions, 12, pageCode);
        map.put("comments", comments);//conditions-->>map存放数据,pageCode-->>分页条数,从第几个开始
        return R.isOk().data(map);
    }



    /**
     * 保存评论信息
     * @title saveComment
     * @author cz
     * @date 2019/3/2 10:47
     * @param comments(comments,blogId,username)
     * @Description TODO 必填参数comments,
     *                  blogId,
     *                  username
     *                  返回success则为注册成功
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * 
     */
    @Transactional
    @PostMapping("saveComment")
    @ApiOperation(value="保存评论信息", notes="根据url的信息来保存评论信息")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> saveComment( @ApiParam(name="评论对象",value="必填参数comments，blogId，username",required=true)Comments comments) {
        User user = userService.selectUserByUsername(comments.getUsername());
        if(user.getExperience() > 0){
            String format = DateTime.getTimeByDateToString(new Date());//创建当前时间以yyyy-MM-dd格式
            int count = commentsService.selectCommentTimeCountByTime(format,comments.getUsername());//返回前一次当天写博客的次数
            if(count < 3){//回帖小于三次加8经验，超过不加
                userService.updateExperienceByUid(user.getUid(),user.getExperience() + 5);//写入数据库
            }
            blogService.updateBlogByBlogId(comments.getBlogId(),2);//评论数+1
            commentsService.saveComment(comments);
        }else {
            return R.isFail().msg("当前等级为0级,答题后可写评论");
        }
        return R.isFail().msg("参数错误");
    }

    
    /**
     * 删除评论信息
     * @title deleteComment
     * @author cz
     * @date 2019/3/2 10:50
     * @param cId
     * @Description TODO 删除信息,必填字段cId删除成功返回result为success
     * @return java.lang.String
     * 
     */
    @PostMapping("/deleteComment")
    @ApiOperation(value="删除评论信息", notes="根据url的信息来删除评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cId", value = "评论识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> deleteComment(String cId){
        commentsService.deleteComment(cId);
        return R.isOk().data("success");
    }
}
