package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import com.gz.ik.entity.Admin;



public interface AdminDao {
	List<Admin> queryadminlist(Map<String, Object> pageMap);
	
	/*通过账号判断是否有这个管理员*/
	Admin queryadmin (String account);
	
	/*注册新的管理员*/
	int insertadmin(Admin admin);
	
	/*更新管理员*/
	int updateadmin(Admin admin);
	
	/*通过账号删除管理员*/
	boolean deleteadmin(String account);
}
