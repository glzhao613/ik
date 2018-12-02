package com.gz.ik.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dao.TeacherDao;
import com.gz.ik.dto.TeacherExecution;
import com.gz.ik.entity.Teacher;
import com.gz.ik.enums.TeacherStateEnum;
import com.gz.ik.service.TeacherService;
import com.gz.ik.util.FileUtil;
import com.gz.ik.util.ImageUtil;

@Service
public class TeacherServicelmpl implements TeacherService {
	
	@Autowired
	TeacherDao teacherDao;

	@Override
	public TeacherExecution getTeacherList() throws RuntimeException {
		List<Teacher> teacherList=teacherDao.queryteacherlist(new Teacher());
		if(teacherList==null||teacherList.size()<=0) {
			return new TeacherExecution(TeacherStateEnum.QUERY_NULL);
		}else {
			return new TeacherExecution(TeacherStateEnum.QUERY_SECCESS,teacherList);	
		}
	}

	@Override
	public TeacherExecution getTeacher(Teacher teacher) throws RuntimeException {
		Teacher g_teacher=null;
		if(teacher==null) {
			return new TeacherExecution(TeacherStateEnum.INPUT_NULL);
		}
		else {
			g_teacher=teacherDao.queryteacher(teacher.getTeacherName());
			if(g_teacher!=null) {
				return new TeacherExecution(TeacherStateEnum.PASS,g_teacher);
			}
			else {
				return new TeacherExecution(TeacherStateEnum.ERRO);
			}
		}
	}

	@Override
	public TeacherExecution insertTeacher(Teacher teacher,CommonsMultipartFile img) throws RuntimeException {
		Teacher i_teacher=null;
		if(teacher==null) {
			return new TeacherExecution(TeacherStateEnum.INPUT_NULL);
		}
		else {
			i_teacher=teacherDao.queryteacher(teacher.getTeacherName());
			if(i_teacher==null) {
				int count=0;
				addTeacherImg(teacher, img);
				count=teacherDao.insertteacher(teacher);
				if(count>0) {
					return new TeacherExecution(TeacherStateEnum.INSERT_PASS);
				}
				else {
					return new TeacherExecution(TeacherStateEnum.INSERT_ERRO);
				}
			}
			else {
				return new TeacherExecution(TeacherStateEnum.INSERT_ERRO);
			}
		}
	}

	@Override
	public TeacherExecution updateTeacher(Teacher teacher,CommonsMultipartFile img) throws RuntimeException {
		Teacher u_teacher=null;
		if(teacher==null) {
			return new TeacherExecution(TeacherStateEnum.INPUT_NULL);
		}
		else {
			if(img!=null) {
				u_teacher=teacherDao.queryteacher(teacher.getTeacherName());
				if(u_teacher==null) {
					return new TeacherExecution(TeacherStateEnum.UPDATE_ERRO);
				}
				FileUtil.deleteFile(u_teacher.getTeacherImg());
				addTeacherImg(teacher,img);
			}
			int count=0;
			count=teacherDao.updateteacher(teacher);
			if(count>0) {
				return new TeacherExecution(TeacherStateEnum.UPDATE_PASS);
			}
			else {
				return new TeacherExecution(TeacherStateEnum.UPDATE_ERRO);
			}
		}
	}

	@Transactional
	@Override
	public TeacherExecution deleteTeacher(Teacher teacher) throws RuntimeException {
		boolean d_teacher=false;
		boolean dc_teacher=false;
		if(teacher==null) {
			return new TeacherExecution(TeacherStateEnum.INPUT_NULL);
		}
		else {
			dc_teacher=teacherDao.deleteteacherC(teacher.getTeacherId());
			if(dc_teacher) {
				d_teacher=teacherDao.deleteteacher(teacher.getTeacherId());
				if(d_teacher) {
					return new TeacherExecution(TeacherStateEnum.DELETE_PASS);
				}
				else {
					return new TeacherExecution(TeacherStateEnum.DELETE_ERRO);
				}
			}
			else {
				return new TeacherExecution(TeacherStateEnum.DELETE_ERRO);
			}
		}
	}

	private void addTeacherImg(Teacher teacher, CommonsMultipartFile img) {
		String dest = FileUtil.getUserImgPath();
		String imgAddr = ImageUtil.generateThumbnail(img, dest);
		teacher.setTeacherImg(imgAddr);
	}





}
