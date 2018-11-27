package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.Course;
import com.gz.ik.entity.UC;
import com.gz.ik.entity.User;

public interface UserDao {

	/**
	 * 通过账号查询用户
	 * @param account
	 * @return User
	 */
	User queryUserByUserAccount(String account);
	
	

	/**
	 * 新建用户
	 * @param user
	 * @return 插入成功的数量
	 */
	int insertUser(User user);
	
	/**
	 * 删除用户
	 * @param account
	 * @return
	 */
	int delUser(String account);
	
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return 操作成功的数量
	 */
	int updataUser(User user);
	
	
	/**
	 * 保存用户购买的课程到U_C表
	 * @param uc
	 * @return 保存成功的数量
	 */
	int savaUserCourse(UC uc);
	
	/**
	 * 删除用户购买的课程
	 * @param uc
	 * @return
	 */
	int delUserCourse(UC uc);
	
	
	/**
	 * 删除用户的所有课程
	 * @param uId
	 * @return
	 */
	int delUserAllCourse(Integer uId);
	
	/**
	 * 查询用户已经购买的课程
	 * @param userId
	 * @return
	 */
	List<Course> queryCourseByUserId(Integer userId);
	

}
