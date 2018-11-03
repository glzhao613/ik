package com.gz.ik.service;

import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.User;

public interface UserService {
	
	UserExecution loginCheck(User user) throws RuntimeException;

}
