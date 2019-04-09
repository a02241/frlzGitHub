package com.frlz.service;


import com.frlz.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	List<User> getAll();

	User selectUser(String account);

	User selectByUid(String uid);

	int checkPhonenumber(String phonenumber);

	int checkEmail(String email);

	String searchUsernameById(String uid);

	User selectUserByUsername(String username);

	User selectUserByUid(String uid);

	String searchUsernameByEmail(String email);

	String check(User user);

	public int selectExperienceByUid(String uid);
	
	public int checkUser(String username,String email,String phoneNumber);

	String registSave(User user);

	void uploadUserIcon(String uid,String icon);

	void updateUser(User user);

	void updateExperienceByUid(String uid,int experience);

	void updatePassword(User user);

	void updatePhonenumber(String uid,String phonenumber);

	void updateEmail(String uid,String email);

	void updateFansNumberAdd(String uid);

	void updateFansNumberReduce(String uid);

	void updateInterestNumberAdd(String uid);

	void updateInterestNumberReduce(String uid);

	String updateProfile(String profile,String uid);

	int checkUserByUid(String uid);

	String  checkPasswordByUId(String uid);

	void deleteUserByUid(String uid);

	void changeSignature(String signature,String uid);

	Map<String,String> getSecurity(String uid);
}
