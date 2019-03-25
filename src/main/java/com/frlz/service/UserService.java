package com.frlz.service;


import com.frlz.pojo.User;

import java.util.List;

public interface UserService {

	List<User> getAll();

	User selectUser(String account);

	User selectByUid(String uid);

	int checkPhonenumber(String phonenumber);

	int checkEmail(String email);

	String searchUsernameById(String uid);

	User selectUserByUsername(String username);

	String searchUsernameByEmail(String email);

	String check(User user);

	String registSave(User user);

	void uploadUserIcon(String username,String icon);

	void updateUser(User user);

	void updatePassword(User user);

	void updatePhonenumber(String uid,String phonenumber);

	void updateEmail(String uid,String email);

	void updateFansNumberAdd(String uid);

	void updateFansNumberReduce(String uid);

	void updateInterestNumberAdd(String uid);

	void updateInterestNumberReduce(String uid);

	String updateProfile(String profile,String uid);

	String  checkPasswordByUId(String uid);

	void deleteUserByUid(String uid);

	void changeSignature(String signature,String uid);
}
