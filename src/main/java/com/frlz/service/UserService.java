package com.frlz.service;


import com.frlz.pojo.User;

import java.util.List;

public interface UserService {

	public List<User> getAll() throws Exception;

	public User selectUser(String account) throws Exception;

	public User selectByUid(String uid) throws Exception;

	public int checkPhonenumber(String phonenumber) throws Exception;

	public int checkEmail(String email) throws Exception;

	public String searchUsernameById(String uid) throws Exception;

	public User selectUserByUsername(String username) throws Exception;

	public String searchUsernameByEmail(String email) throws Exception;

	public String check(User user) throws Exception;

	public String registSave(User user) throws Exception;

	public void uploadUserIcon(String username,String icon) throws Exception;

	public void updateUser(User user) throws Exception;

	public void updatePassword(User user) throws Exception;

	public void updatePhonenumber(String uid,String phonenumber) throws Exception;

	public void updateEmail(String uid,String email) throws Exception;

	void updateFansNumberAdd(String uid) throws Exception;

	void updateFansNumberReduce(String uid) throws Exception;

	void updateInterestNumberAdd(String uid) throws Exception;

	void updateInterestNumberReduce(String uid) throws Exception;

	public String  checkPasswordByUId(String uid) throws Exception;

	public void deleteUserByUid(String uid) throws Exception;
}
