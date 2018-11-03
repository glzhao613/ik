package com.gz.ik.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.UserDao;
import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.User;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserExecution loginCheck(User user) throws RuntimeException {
		
		User t_user=null;
		if(user==null) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		}
		else {
			t_user=userDao.queryUserByUserAccount(user.getUserAccount());
			if(t_user==null) {
				return new UserExecution(UserStateEnum.NO_ACCOUNT);
			}
			else {
				if(!(user.getUserPwd().equals(t_user.getUserPwd()))) {
					return new UserExecution(UserStateEnum.PWD_ERROR);
				}
			}
			
		}
		return new UserExecution(UserStateEnum.PASS,t_user);
	}

}
