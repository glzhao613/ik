package com.gz.ik.service;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.User;

public interface UserService {
	
	UserExecution loginCheck(User user) throws RuntimeException;
	
	UserExecution userAdd(User user) throws RuntimeException;
	
	UserExecution updataUserInfo(User user,CommonsMultipartFile img) throws RuntimeException;
	
	/*		UserExecution userDel(String userAccount) throws RuntimeException;
	
	UserExecution addUserCourse(User user) throws RuntimeException;
	
	UserExecution delUserCourse(User user) throws RuntimeException;
	
	*/
	
	UserExecution updataUserPwd(User user,String newPwd) throws RuntimeException;
	
	
	
	
	
	
	
	

}
