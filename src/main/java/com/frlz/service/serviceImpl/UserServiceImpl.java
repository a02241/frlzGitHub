package com.frlz.service.serviceImpl;

import com.frlz.mapper.UserMapper;
import com.frlz.pojo.User;
import com.frlz.service.UserService;
import com.frlz.util.GetUername;
import com.frlz.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.frlz.util.GetUername.getUsername;

/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }



    @Override
    public List<User> getAll(){
        return userMapper.selectAll();
    }

    @Override
    public User selectUser(String account){
        return userMapper.loginSelect(account);
    }

    @Override
    public User selectByUid(String uid) {
        return userMapper.selectByUid(uid);
    }

    @Override
    public int checkPhonenumber(String phonenumber){
        return userMapper.checkPhonenumber(phonenumber);
    }

    @Override
    public int checkEmail(String email){
        return userMapper.checkEmail(email);
    }

    @Override
    public String searchUsernameById(String uid){
        return userMapper.searchUsernameById(uid);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }


    @Override
    public String searchUsernameByEmail(String email){
        return userMapper.searchUsernameByEmail(email);
    }

    @Override
    public String check(User user) {
        String username = GetUername.getUsername();
        boolean checkUsername = true;
        int checkPhonenumber = userMapper.checkPhonenumber(user.getPhonenumber());
        int checkEmail = userMapper.checkEmail(user.getEmail());
        //验证用户名是否被注册
        while (checkUsername) {
            username = GetUername.getUsername();
            user.setUsername(username);
            userMapper.checkUsername(user.getUsername());
            checkUsername = false;
        }
        //验证电话是否被注册
        if(checkPhonenumber != 0) {
            return "2";
        }
        //验证邮箱是否被注册
        if(checkEmail != 0) {
            return "3";
        }
        return username;
    }

    @Override
    public String registSave(User user) {
        //默认头像
        user.setIcon("default.png");
        //默认投资年龄
        user.setInvestmentage(1);
        //默认投资简介
        user.setPrivacy(1);
        //默认状态码
        user.setState(1);
        /*//默认邀请码
        user.setCode("a123");*/
        //注册时间
        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        Date newDate = null;//创建当前时间以yyyy-MM-dd HH:mm:ss格式
        try {
            newDate = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setRegistTime(newDate);
        //默认粉丝数
        user.setFansNumber(0);
        //关注人数
        user.setInterestNumber(0);
        //MD5加密
        user.setPassword(MD5.MD5Encode("fr2018<%" + user.getPassword()  + "%>lz1220"));
        user.setExperience(0);
        userMapper.registSave(user);
        user = userMapper.selectUserByUsername(user.getUsername());
        return user.getUid();
    }

    @Override
    public void uploadUserIcon(String username, String icon) {
        userMapper.uploadUserIcon(username,icon);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }

    @Override
    public void updatePhonenumber(String uid, String phonenumber) {
        userMapper.updatePhonenumber(uid,phonenumber);
    }

    @Override
    public void updateEmail(String uid, String email) {
        userMapper.updateEmail(uid,email);
    }

    @Override
    public void updateFansNumberAdd(String uid) {
        userMapper.updateFansNumberAdd(uid);
    }

    @Override
    public void updateFansNumberReduce(String uid) {
        userMapper.updateFansNumberReduce(uid);
    }

    @Override
    public void updateInterestNumberAdd(String uid) {
        userMapper.updateInterestNumberAdd(uid);
    }

    @Override
    public void updateInterestNumberReduce(String uid) {
        userMapper.updateInterestNumberReduce(uid);
    }

    @Override
    public String checkPasswordByUId(String uid) {
        return userMapper.checkPasswordByUId(uid);
    }

    @Override
    public void deleteUserByUid(String uid) {
        userMapper.deleteByUid(uid);
    }
}
