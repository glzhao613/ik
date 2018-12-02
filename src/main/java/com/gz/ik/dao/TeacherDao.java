package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.Teacher;



public interface TeacherDao {
	List<Teacher> queryteacherlist(Teacher teacher);
	
	
	Teacher queryteacher (String teacherName);
	

	int insertteacher(Teacher teacher);
	

	int updateteacher(Teacher teacher);
	

	boolean deleteteacher(int teacherId);
	boolean deleteteacherC(int teacherId);
}
