package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
/**
 * @author cz
 */
@RestControllerAdvice
@RestController
@Api(value="博客controller",tags={"博客操作接口"})
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public BlogController(BlogService blogService,UserService userService){
        this.blogService = blogService;
        this.userService = userService;
    }




    /**
     *
     * @title searchBlog
     * @create by: cz
     * @description 必填参数 pageCode uid
     * @create time: 2019/4/18 17:25
     * @param pageCode
     * @param uid
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */
    @Transactional
    @PostMapping("/searchBlog")
    @ApiOperation(value="展示博客信息", notes="根据url的信息来展示博客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageCode", value = "页码(默认为1)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> searchBlog(@RequestParam(defaultValue="1") int pageCode, @RequestParam(defaultValue="")String uid)  {
        HashMap<String,Object> map = blogService.searchBlog(pageCode,uid,1);
        String username;
        if(uid.equals("")) {
            map.put("uid","用户名已过期,请重新登录");
            return R.isOk().data(map);
        }else {
            username = userService.searchUsernameById(uid);//根据uid查询用户名
        }
        map.put("username", username);
        if(username.equals("")) {
            map.put("username","用户名已过期,请重新登录");
            return R.isOk().data(map);
        }
        return R.isOk().data(map);
    }


    /**
     *
     * @title searchBlogChoice
     * @create by: cz
     * @description 必填参数pageCode
     * @create time: 2019/4/18 17:26
     * @param pageCode
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */
    @Transactional
    @PostMapping("/searchBlogChoice")
    @ApiOperation(value="精选博客", notes="根据url的信息来展示精选博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageCode", value = "页码(默认为1)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> searchBlogChoice(@RequestParam(defaultValue="1") int pageCode) {
        HashMap<String,Object> map = blogService.searchBlog(pageCode,"",2);
        return R.isOk().data(map);
    }

    /**
     * 添加博客
     * @title insertBlog
     * @author cz
     * @date 2019/3/1 15:48
     * @param blog (必填参数message(内容),summary(摘要),title(主题))
     * @param uid
     * @Description  必填字段uid,message(内容),summary(摘要),title(主题),返回为true则成功注册,返回false则uid为空
     * @return boolean
     *  Exception
     */
    @Transactional
    @PostMapping("/insertBlog")
    @ApiOperation(value="添加博客", notes="根据url的信息来添加博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Boolean> insertBlog( @ApiParam(name="博客对象",value="必填参数message(内容),summary(摘要),title(主题)",required=true)Blog blog,String uid){
        if(uid.trim().length() == 0 || uid == null){
            return R.isFail().msg("uid为空");
        }else {
            User user = userService.selectByUid(uid);
            if (user != null){
                if (user.getExperience() > 0) {
                    int count = blogService.insertBlog(blog, uid);
                    if (count < 1) {//每天发第一次贴加8经验
                        userService.updateExperienceByUid(uid, user.getExperience() + 8);//写入数据库
                    }
                }else {
                    return R.isFail().msg("当前等级为0级,答题后可写博客");
                }
            }else {
                return R.isFail().msg("uid错误");
            }
        }
        return R.isOk().msg("success");
    }


    
    /**
     * 增加阅读数
     * @title addReadNumber
     * @create by: cz
     * @Description  必填参数blogId 返回阅读数+1字符串
     * @create time: 2019/3/16 14:29
     * @param blogId
     * 
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("addReadNumber")
    @ApiOperation(value="增加阅读数", notes="根据url的信息来增加阅读数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId", value = "博客识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> addReadNumber(String blogId){
        blogService.updateBlogByBlogId(blogId,4);
        return R.isOk().msg("阅读数+1");
    }


    /**
     *  增加转发数
     * @title addForwordNumber
     * @create by: cz
     * @Description  必填参数blogId，返回转发数+1字符串
     * @create time: 2019/3/16 14:29
     * @param blogId
     * 
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("addForwordNumber")
    @ApiOperation(value="增加转发数", notes="根据url的信息来增加转发数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId", value = "博客识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> addForwordNumber(String blogId) {
        blogService.updateBlogByBlogId(blogId,3);
        return R.isOk().msg("转发数+1");
    }
}
