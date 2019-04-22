package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.LoginLog;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.LoginLogService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
/**
 * @author cz
 */
@RestControllerAdvice
@RestController
@Api(value="管理员controller",tags={"管理员信息操作接口"})
public class ManagerController {

    private final UserService userService;
    private final BlogService blogService;
    private final LoginLogService loginLogService;

    @Autowired
    public ManagerController(UserService userService,BlogService blogService,LoginLogService loginLogService){
        this.userService = userService;
        this.blogService = blogService;
        this.loginLogService = loginLogService;
    }

    
    /**
     * 管理员登录
     * @title managerLogin
     * @create by: cz
     * @description  必填字段manager，password
     * @create time: 2019/3/13 16:04
     * @param manager
     * @param password
     * 
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("/managerLogin")
    @ApiOperation(value="管理员登录", notes="根据url的信息来管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manager", value = "管理员用户名", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "管理员密码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> managerLogin(@Param("manager") String manager, @Param("password") String password){
        if ("managercz".equals(manager)){
            if ("123".equals(password)){
                return R.isOk().msg("success");
            }
            return R.isFail().msg("password is wrong");
        }
        return R.isFail().msg("manager not exist");
    }

    
    /**
     * 删除用户
     * @title deleteUser
     * @create by: cz
     * @description  必填参数uid
     * @create time: 2019/3/13 16:08
     * @param uid
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("/deleteUser")
    @ApiOperation(value="删除用户", notes="根据url的信息来删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> deleteUser(String uid){
        userService.deleteUserByUid(uid);
        return R.isOk().msg("success");
    }

    
    /**
     * 查询所有用户
     * @title selectAllUser
     * @create by: cz
     * @description  查询所有用户
     * @create time: 2019/3/13 16:10
     * @param
     * @return java.util.List<com.frlz.pojo.User>
     * @version V1.0
     */
    @PostMapping("/selectAllUser")
    @ApiOperation(value="查询所有用户", notes="根据url的信息来查询所有用户")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<User>> selectAllUser(){
        return R.isOk().data(userService.getAll());
    }

    @PostMapping("/getblog")
    public R<List<Blog>> getblog(int a){

        return R.isOk().data(blogService.selectFiftyBlog((a - 1)* 20));
    }



    /**
     *
     * @title deleteBlog
     * @create by: cz
     * @description 必填参数uid 删除博客
     * @create time: 2019/4/18 17:37
     * @param blogId
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */
    @PostMapping("/deleteBlog")
    @ApiOperation(value="删除博客", notes="根据url的信息来删除博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId", value = "博客识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> deleteBlog(String blogId){
        blogService.deleteBlog(blogId);
        return R.isOk().msg("success");
    }


    /**
     * 按照日期查询博客所有信息
     * @title getBlogByDate
     * @create by: cz
     * @description  必填参数:date，返回博客该日期所有数据
     * @create time: 2019/3/14 15:24
     * @param date
     * @return java.util.List<com.frlz.pojo.Blog>
     * @version V1.0
     */
    @PostMapping("/getBlogByDate")
    @ApiOperation(value="按照日期查询博客所有信息", notes="根据url的信息来按照日期查询博客所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(yyyy-mm)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<Blog>> getBlogByDate(String date){
        return R.isOk().data(blogService.selectBlogByMonth(date));
    }


    /**
     * 按照月份查询博客所有信息
     * @title getBlogByMonth
     * @create by: cz
     * @description  必填参数:date 返回博客该月份所有数据
     * @create time: 2019/3/14 15:27
     * @param date
     * @return java.util.List<com.frlz.pojo.Blog>
     * @version V1.0
     */
    @PostMapping("/getBlogByMonth")
    @ApiOperation(value="按照月份查询博客所有信息", notes="根据url的信息来按照月份查询博客所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(yyyy-mm-dd)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<Blog>> getBlogByMonth(String date){
        return R.isOk().data(blogService.selectBlogByMonth(date));
    }


    /**
     * 获取所有登录日志
     * @title getAllLoginLog
     * @create by: cz
     * @description  返回所有登录日志
     * @create time: 2019/3/14 15:30
     * @param
     * @return java.util.List<com.frlz.pojo.LoginLog>
     * @version V1.0
     */
    @PostMapping("/getAllLoginLog")
    @ApiOperation(value="获取所有登录日志", notes="根据url的信息来获取所有登录日志")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<LoginLog>> getAllLoginLog(){
        return R.isOk().data(loginLogService.getAllLoginLog());
    }


    /**
     * 根据日期查询登录日志
     * @title getLoginLogByDate
     * @create by: cz
     * @description  必填参数date，返回该日期的所有登录日志
     * @create time: 2019/3/14 15:31
     * @param date
     * @return java.util.List<com.frlz.pojo.LoginLog>
     * @version V1.0
     */
    @PostMapping("/getLoginLogByDate")
    @ApiOperation(value="根据日期查询登录日志", notes="根据url的信息来根据日期查询登录日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(yyyy-mm-dd)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<LoginLog>> getLoginLogByDate(String date){
        return R.isOk().data(loginLogService.selectLoginLogByDate(date));
    }


}
