package com.frlz.controller;

import com.frlz.pojo.User;
import com.frlz.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    private UserService userService;

    @RequestMapping("/managerLogin")
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

    public String managerLogin(@Param("manager") String manager, @Param("password") String password){
        if ("managercz".equals(manager)){
            if ("123".equals(password)){
                return "success";
            }
            return "password is wrong";
        }
        return "manager not exist";
    }

    @RequestMapping("/deleteUser")
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

    public String deleteUser(String uid){
        userService.deleteUserByUid(uid);
        return "success";
    }

    @RequestMapping("/selectAllUser")
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

    public List<User> selectAllUser(){

        return userService.getAll();
    }
}
