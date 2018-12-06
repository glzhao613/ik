package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Admin;
import com.gz.ik.entity.Course;



public interface AdminDao {
	
	/*通过账号判断是否有这个管理员*/
	Admin queryadmin (String account);
	
	List<Admin> queryAdminList(@Param("admin") Admin admin,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryAdminCount();
	
	/*查询管理员所管理的模块URL*/
	Admin querAdminModuleUrl(@Param("admin") Admin admin);
	
	/*注册新的管理员*/
	int insertadmin(Admin admin);
	
	/*更新管理员*/
	int updateadmin(Admin admin);
	
	/*通过账号删除管理员*/
	boolean deleteadmin(int adminid);
}
