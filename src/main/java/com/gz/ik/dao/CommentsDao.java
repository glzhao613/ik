package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Comments;
import com.gz.ik.entity.Course;

public interface CommentsDao {
	
	/**
	 * 查询评论
	 * @param comments
	 * @return
	 */
	List<Comments> queryComments(Comments comments);
	
	/**
	 *删除评论
	 * @param uc
	 * @return
	 */
	int delComments(Comments comments);
	
	/**
	 * 分页查询
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Comments> queryCommentsList(@Param("course") Course course,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	

}
