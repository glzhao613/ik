package com.gz.ik.dao;

import com.gz.ik.entity.Admin;



public interface AdminDao {
	/*通过账号判断是否有这个管理员*/
	Admin queryadmin (String account);
	
	/*注册新的管理员*/
	int insertadmin(Admin admin);
}
