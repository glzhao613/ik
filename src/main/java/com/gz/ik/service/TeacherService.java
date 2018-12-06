package com.gz.ik.service;



import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.TeacherExecution;
import com.gz.ik.entity.Teacher;


public interface TeacherService {
	
	TeacherExecution getTeacherList(Teacher teacher) throws RuntimeException;
	
	TeacherExecution getTeacher(Teacher teacher) throws RuntimeException;
	
	TeacherExecution insertTeacher(Teacher teacher,CommonsMultipartFile img) throws RuntimeException;
	
	TeacherExecution updateTeacher(Teacher teacher,CommonsMultipartFile img) throws RuntimeException;
	
	TeacherExecution deleteTeacher(Teacher teacher) throws RuntimeException;

	TeacherExecution showTeacherList(int pageIndex, int pageSize) throws RuntimeException;
}
