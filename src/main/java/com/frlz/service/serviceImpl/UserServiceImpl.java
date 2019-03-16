package com.frlz.service.serviceImpl;

import com.frlz.mapper.UserMapper;
import com.frlz.pojo.User;
import com.frlz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

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
        int checkUsername = userMapper.checkUsername(user.getUsername());
        int checkPhonenumber = userMapper.checkPhonenumber(user.getPhonenumber());
        int checkEmail = userMapper.checkEmail(user.getEmail());
        //验证用户名是否被注册
        if(checkUsername != 0) {
            return "1";
        }
        //验证电话是否被注册
        if(checkPhonenumber != 0) {
            return "2";
        }
        //验证邮箱是否被注册
        if(checkEmail != 0) {
            return "3";
        }
        return "5";
    }

    @Override
    public void registSave(User user) {
        userMapper.registSave(user);
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
    public String checkPasswordByUId(String uid) {
        return userMapper.checkPasswordByUId(uid);
    }

    @Override
    public void deleteUserByUid(String uid) {
        userMapper.deleteByUid(uid);
    }
}
