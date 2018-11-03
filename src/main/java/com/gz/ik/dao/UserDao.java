package com.gz.ik.dao;

import com.gz.ik.entity.User;

public interface UserDao {

	/**
	 * 通过账号查询用户
	 * @param account
	 * @return User
	 */
	User queryUserByUserAccount(String account);

}
