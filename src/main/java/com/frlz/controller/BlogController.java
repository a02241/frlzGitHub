package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestControllerAdvice
@RestController
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public BlogController(BlogService blogService,UserService userService){
        this.blogService = blogService;
        this.userService = userService;
    }




    @Transactional
    @PostMapping("/searchBlog")
    /**
     * 展示博客信息
     * @title searchBlog
     * @author cz
     * @date 2019/3/1 14:58
     * @param blog
     * @param pageCode
     * @param uid
     * @Description: TODO 必填参数uid 选填参数pageCode(分页页码，默认从第1个开始12条数据，每次请求为下12条条数据)
     *                  返回值:uid如果显示用户名已过期,请重新登录，则uid为空，username一样
     *                  pageBean分页类,类中datas返回Blog和User属性
     *                  Myuid为uid
     *                  pageCode从第几个开始
     *                  pageSize返回blog数量
     *                  allCount总数量
     *                  allPages总共翻页数量
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @throws Exception
     */

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

    @Transactional
    @PostMapping("/searchBlogChoice")
    /**
     * TODO 精选博客
     * @title searchBlogChoice
     * @create by: cz
     * @description: TODO 选填参数pageCode 默认为1
     * @create time: 2019/3/20 17:44
     * @Param: pageCode
     * @throws
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */

    public R<HashMap<String,Object>> searchBlogChoice(@RequestParam(defaultValue="1") int pageCode) {
        HashMap<String,Object> map = blogService.searchBlog(pageCode,"",2);
        return R.isOk().data(map);
    }
    @Transactional
    @PostMapping("/insertBlog")
    /**
     * 添加博客
     * @title insertBlog
     * @author cz
     * @date 2019/3/1 15:48
     * @param blog
     * @param uid
     * @Description: TODO 必填字段uid,message(内容),summary(摘要),title(主题),返回为true则成功注册,返回false则uid为空
     * @return boolean
     * @throws Exception
     */

    public R<Boolean> insertBlog(Blog blog,String uid){
        if(uid.trim().length() == 0 || uid == null){
            return R.isFail();
        }else {
            int count = blogService.insertBlog(blog,uid);
            if (count < 1){//每天发第一次贴加8经验
                User user = userService.selectByUid(uid);
                if (user.getExperience()<0){//0级不给经验
                    return R.isOk().data("当前等级为0级,答题后可增加经验");
                }else {
                    user.setExperience(user.getExperience() + 8);//发帖加8经验
                    userService.updateExperienceByUid(uid);//写入数据库
                }
            }
        }
        return R.isOk();
    }


    @PostMapping("addReadNumber")
    /**
     * 增加阅读数
     * @title addReadNumber
     * @create by: cz
     * @description: TODO 必填参数blogId 返回阅读数+1字符串
     * @create time: 2019/3/16 14:29
     * @Param: blogId
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> addReadNumber(String blogId){
        blogService.updateBlogByBlogId(blogId,4);
        return R.isOk().msg("阅读数+1");
    }

    @PostMapping("addForwordNumber")
    /**
     * TODO 增加转发数
     * @title addForwordNumber
     * @create by: cz
     * @description: TODO 必填参数blogId，返回转发数+1字符串
     * @create time: 2019/3/16 14:29
     * @Param: blogId
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    public R<String> addForwordNumber(String blogId) {
        blogService.updateBlogByBlogId(blogId,3);
        return R.isOk().msg("转发数+1");
    }

}
