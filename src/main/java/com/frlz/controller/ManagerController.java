package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.LoginLog;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.LoginLogService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RestController
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

    @PostMapping("/managerLogin")
    /**
     * 管理员登录
     * @title managerLogin
     * @create by: cz
     * @description: TODO 必填字段manager，password
     * @create time: 2019/3/13 16:04
     * @Param: manager
     * @Param: password
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> managerLogin(@Param("manager") String manager, @Param("password") String password){
        if ("managercz".equals(manager)){
            if ("123".equals(password)){
                return R.isOk().msg("success");
            }
            return R.isFail().msg("password is wrong");
        }
        return R.isFail().msg("manager not exist");
    }

    @PostMapping("/deleteUser")
    /**
     * 删除用户
     * @title deleteUser
     * @create by: cz
     * @description: TODO 必填参数uid
     * @create time: 2019/3/13 16:08
     * @Param: uid
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> deleteUser(String uid){
        userService.deleteUserByUid(uid);
        return R.isOk().msg("success");
    }

    @PostMapping("/selectAllUser")
    /**
     * 查询所有用户
     * @title selectAllUser
     * @create by: cz
     * @description: TODO
     * @create time: 2019/3/13 16:10
     * @Param:
     * @throws
     * @return java.util.List<com.frlz.pojo.User>
     * @version V1.0
     */

    public R<List<User>> selectAllUser(){
        return R.isOk().data(userService.getAll());
    }

    @PostMapping("/getblog")
    public R<List<Blog>> getblog(int a){

        return R.isOk().data(blogService.selectFiftyBlog((a - 1)* 20));
    }

    @PostMapping("/deleteBlog")
    public R<String> deleteBlog(String blogId){
        blogService.deleteBlog(blogId);
        return R.isOk().msg("success");
    }

    @PostMapping("/getBlogByDate")
    /**
     * 按照日期查询博客所有信息
     * @title getBlogByDate
     * @create by: cz
     * @description: TODO 必填参数:date，返回博客该日期所有数据
     * @create time: 2019/3/14 15:24
     * @Param: date
     * @throws
     * @return java.util.List<com.frlz.pojo.Blog>
     * @version V1.0
     */

    public R<List<Blog>> getBlogByDate(String date){
        return R.isOk().data(blogService.selectBlogByMonth(date));
    }

    @PostMapping("/getBlogByMonth")
    /**
     * 按照月份查询博客所有信息
     * @title getBlogByMonth
     * @create by: cz
     * @description: TODO 必填参数:date 返回博客该月份所有数据
     * @create time: 2019/3/14 15:27
     * @Param: date
     * @throws
     * @return java.util.List<com.frlz.pojo.Blog>
     * @version V1.0
     */

    public R<List<Blog>> getBlogByMonth(String date){
        return R.isOk().data(blogService.selectBlogByMonth(date));
    }

    @PostMapping("/getAllLoginLog")
    /**
     * 获取所有登录日志
     * @title getAllLoginLog
     * @create by: cz
     * @description: TODO 返回所有登录日志
     * @create time: 2019/3/14 15:30
     * @Param:
     * @throws
     * @return java.util.List<com.frlz.pojo.LoginLog>
     * @version V1.0
     */

    public R<List<LoginLog>> getAllLoginLog(){
        return R.isOk().data(loginLogService.getAllLoginLog());
    }

    @PostMapping("/getLoginLogByDate")
    /**
     * 根据日期查询登录日志
     * @title getLoginLogByDate
     * @create by: cz
     * @description: TODO 必填参数date，返回该日期的所有登录日志
     * @create time: 2019/3/14 15:31
     * @Param: date
     * @throws
     * @return java.util.List<com.frlz.pojo.LoginLog>
     * @version V1.0
     */

    public R<List<LoginLog>> getLoginLogByDate(String date){
        return R.isOk().data(loginLogService.selectLoginLogByDate(date));
    }


}
