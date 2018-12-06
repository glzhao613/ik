package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dao.CommentsDao;
import com.gz.ik.dao.CourseDao;
import com.gz.ik.dao.UCDao;
import com.gz.ik.dto.CourseDeleteExecution;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseInsertExecution;
import com.gz.ik.entity.Comments;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.Files;
import com.gz.ik.entity.UC;
import com.gz.ik.enums.CourseDeleteStateEnum;
import com.gz.ik.enums.CourseInsertStateEnum;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.service.CourseService;
import com.gz.ik.util.FileUtil;
import com.gz.ik.util.ImageUtil;
import com.gz.ik.util.PageCalculator;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private UCDao ucDao;
	
	
	
	@Override
	public CourseExecution getCourseList() throws RuntimeException {
		List<Course> courseList=courseDao.queryCourse(new Course());
		if(courseList==null||courseList.size()<=0) {
			return new CourseExecution(CourseStateEnum.QUERY_NULL);
		}else {
			return new CourseExecution(CourseStateEnum.QUERY_SECCESS,courseList);		
		}
	}

	@Override
	public CourseExecution getCourse(Course course) throws RuntimeException {
		Course g_course=courseDao.queryCourseName(course.getCourseName());
		if(g_course!=null) {
			return new CourseExecution(CourseStateEnum.QUERY_SECCESS,g_course);
		}
		else {
			return new CourseExecution(CourseStateEnum.QUERY_NULL);
		}
	}
	
	@Override
	public CourseInsertExecution insertCourse(Course course,CommonsMultipartFile img) throws RuntimeException {
		Course ig_course=null;
		if(course==null || img==null) {
			return new CourseInsertExecution(CourseInsertStateEnum.INPUT_NULL);
		}
		else {
			ig_course=courseDao.queryCourseName(course.getCourseName());
			if(ig_course==null) {
				addCourseImg(course, img);
				int i_course=courseDao.insertCourse(course);
				if(i_course>0) {
					return new CourseInsertExecution(CourseInsertStateEnum.QUERY_SECCESS,course);
				}
				else {
					return new CourseInsertExecution(CourseInsertStateEnum.ERRO);
				}
			}
			else {
				return new CourseInsertExecution(CourseInsertStateEnum.QUERY_ERRO);
			}
		}
	}

	@Transactional
	@Override
	public CourseDeleteExecution deleteCourse(Course course,Comments comments,UC uc,Files files) throws RuntimeException {
		if(course==null) {
			return new CourseDeleteExecution(CourseDeleteStateEnum.INPUT_NULL);
		}
		else {
			boolean d_course=false;
			int d_comments=0;
			int d_uc=0;
			d_comments=commentsDao.delComments(comments);
			if(d_comments<=0) {
				return new CourseDeleteExecution(CourseDeleteStateEnum.ERRO);
			}
			d_uc=ucDao.delUC(uc);
			if(d_uc<=0) {
				return new CourseDeleteExecution(CourseDeleteStateEnum.ERRO);
			}
			d_course=courseDao.deleteCourse(course.getCourseId());
			if(d_course) {
				return new CourseDeleteExecution(CourseDeleteStateEnum.PASS,course);
			}
			else {
				return new CourseDeleteExecution(CourseDeleteStateEnum.ERRO);
			}
		}
	}

	private void addCourseImg(Course course, CommonsMultipartFile img) {
		String dest = FileUtil.getUserImgPath();
		String imgAddr = ImageUtil.generateThumbnail(img, dest);
		course.setCourseImg(imgAddr);
	}

	@Override
	public CourseExecution updateCourse(Course course, CommonsMultipartFile img) throws RuntimeException {
		Course q_course=null;
		int qc_course=0;
		if(course==null && img==null) {
			return new CourseExecution(CourseStateEnum.UPDATE_NULL);
		}
		else {
			if(img!=null) {
				q_course=courseDao.queryCourseId(course.getCourseId());
				FileUtil.deleteFile(q_course.getCourseImg());
				addCourseImg(course,img);
			}
			qc_course=courseDao.updateCourse(course);
			if(qc_course>0) {
				return new CourseExecution(CourseStateEnum.UPDATE_PASS);
			}
			else {
				return new CourseExecution(CourseStateEnum.UPDATE_ERRO);
			}
		}
	}

	@Override
	public CourseExecution showCourseList(Course course,int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Course> courseList = null;
		courseList =  courseDao.queryCourseList(course, rowIndex, pageSize);
		int count = courseDao.queryCourseCount();
		CourseExecution ce = new CourseExecution();
		if (courseList != null && courseList.size()>0) {
			ce.setState(CourseStateEnum.GET_SECCESS.getState());
			ce.setCourseList(courseList);
			ce.setCount(count);
		} else {
			ce.setState(CourseStateEnum.GET_FALSE.getState());
		}
		return ce;
	}

	@Override
	public CourseExecution showCourse(Course course, int pageIndex, int pageSize) throws RuntimeException {
		
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Course> courseList = null;
		courseList = courseDao.getCourseList(course,rowIndex, pageSize);
		int count = courseDao.queryCourseCount();
		CourseExecution ce = new CourseExecution();
		if (courseList != null && courseList.size() > 0) {
			ce.setState(CourseStateEnum.GET_SECCESS.getState());
			ce.setCourseList(courseList);
			ce.setCount(count);
		} else {
			ce.setState(CourseStateEnum.GET_FALSE.getState());
		}
		return ce;
	}
	
	
}
