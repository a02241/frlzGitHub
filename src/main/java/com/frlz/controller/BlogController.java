package com.frlz.controller;

import com.frlz.pojo.Blog;
import com.frlz.pojo.User;
import com.frlz.service.BlogService;
import com.frlz.service.UserService;
import com.frlz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;





    @Transactional
    @RequestMapping("/searchBlog")
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

    public HashMap<String,Object> searchBlog(Blog blog, @RequestParam(defaultValue="0") int pageCode,@RequestParam(defaultValue="")String uid) throws Exception {
        HashMap<String,Object> map = new HashMap<>();
        String username = "";
        Map<String,Object> conditions = new HashMap<String,Object>();
        if(uid.trim().length() > 0 || uid != null) {
            conditions.put("uid",uid);//把uid放入map集合中
        }
        PageBean pb = blogService.findBy(conditions, 12, pageCode);//conditions-->>map存放数据,pageCode-->>分页条数,从第几个开始
        if(uid.equals("")) {
            map.put("uid","用户名已过期,请重新登录");
            return map;
        }else {
            username = userService.searchUsernameById(uid);//根据uid查询用户名
        }
        map.put("pageBean", pb);
        map.put("username", username);
        map.put("pageCode", pageCode);
        map.put("Myuid", uid);
        if(username.equals("")) {
            map.put("username","用户名已过期,请重新登录");
            return map;
        }
        return map;
    }

    @Transactional
    @RequestMapping("/insertBlog")
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

    public boolean insertBlog(Blog blog,String uid)throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        Date newDate =sdf.parse(format);//创建当前时间以yyyy-MM-dd HH:mm:ss格式
        if(uid.trim().length() == 0 || uid == null){
            return false;
        }else {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String format2 = sdf2.format(date);//创建当前时间以yyyy-MM-dd格式
            int count = blogService.selectBlogCountByDateAndUid(format2,uid);
            if (count < 1){//每天发第一次贴加8经验
            User user = userService.selectByUid(uid);
            int experience = user.getExperience() + 8;//发帖加8经验
            user.setExperience(experience);
            userService.updateUser(user);//写入数据库
            }
            blog.setTime(newDate);
            blog.setLikes(0);
            blog.setDislike(0);
            blog.setFansNumber(0);
            blog.setCommentsNumber(0);
            blog.setForwordNumber(0);
            blog.setReadNumber(0);
            blog.setUid(uid);
        blogService.insertBlog(blog);}
        return true;
    }
}
