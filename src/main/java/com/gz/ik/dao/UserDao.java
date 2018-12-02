package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Course;
import com.gz.ik.entity.User;

public interface UserDao {

	/**
	 * 通过账号查询用户
	 * @param account
	 * @return User
	 */
	User queryUserByUserAccount(String account);
	
	/**
	 * 分页查询用户
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<User> queryUserList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 查询用户总数
	 * @return
	 */
	int queryUserCount();

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
	 * 查询用户已经购买的课程
	 * @param userId
	 * @return
	 */
	List<Course> queryCourseByUserId(Integer userId);
	

}
