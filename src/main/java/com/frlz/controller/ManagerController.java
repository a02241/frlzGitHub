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
    public String deleteUser(String uid){
        userService.deleteUserByUid(uid);
        return "success";
    }

    @RequestMapping("/selectAllUser")
    public List<User> selectAllUser(){

        return userService.getAll();
    }
}
