package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Teacher;



public interface TeacherDao {
	List<Teacher> queryteacherlist(Teacher teacher);
	
	List<Teacher> getteacherlist(Teacher teacher);
	
	
	Teacher queryteacher (String teacherName);
	
	List<Teacher> queryTeacherList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryTeacherCount();

	int insertteacher(Teacher teacher);
	

	int updateteacher(Teacher teacher);
	

	boolean deleteteacher(int teacherId);
	boolean deleteteacherC(int teacherId);
}
