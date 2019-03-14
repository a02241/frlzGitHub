package com.frlz.service;


import com.frlz.pojo.User;

import java.util.List;

public interface UserService {

	public List<User> getAll();

	public User selectUser(String account);

	public User selectByUid(String uid);

	public int checkPhonenumber(String phonenumber);

	public int checkEmail(String email);

	public String searchUsernameById(String uid);

	public User selectUserByUsername(String username);

	public String searchUsernameByEmail(String email);

	public String check(User user);

	public void registSave(User user);

	public void uploadUserIcon(String username,String icon);

	public void updateUser(User user);

	public void updatePassword(User user);

	public String  checkPasswordByUId(String uid);

	public void deleteUserByUid(String uid);
}
