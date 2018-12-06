package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.ik.dao.CourseTypeDao;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseTypeExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.CourseType;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.enums.CourseTypeStateEnum;
import com.gz.ik.service.CourseTypeService;
import com.gz.ik.util.PageCalculator;

@Service
public class CourseTypeServicelmpl implements CourseTypeService {
	
	@Autowired
	CourseTypeDao courseTypeDao;

	@Override
	public CourseTypeExecution getCourseTypeList() throws RuntimeException {
		List<CourseType> courseTypeList=courseTypeDao.queryCourseTypelist(new CourseType());
		if(courseTypeList==null||courseTypeList.size()<=0) {
			return new CourseTypeExecution(CourseTypeStateEnum.QUERY_ERRO);
		}else {
			return new CourseTypeExecution(CourseTypeStateEnum.QUERY_SECCESS,courseTypeList);		
		}
	}

	@Override
	public CourseTypeExecution getCourseType(CourseType courseType) throws RuntimeException {
		CourseType q_courseType=null;
		if(courseType==null) {
			return new CourseTypeExecution(CourseTypeStateEnum.INPUT_NULL);
		}
		else {
			q_courseType=courseTypeDao.queryCourseType(courseType);
			if(q_courseType!=null) {
				return new CourseTypeExecution(CourseTypeStateEnum.QUERY_PASS,q_courseType);
			}
			else {
				return new CourseTypeExecution(CourseTypeStateEnum.ERRO);
			}
		}
	}

	@Override
	public CourseTypeExecution insertCourseType(CourseType courseType) throws RuntimeException {
		CourseType i_courseType=null;
		if(courseType==null) {
			return new CourseTypeExecution(CourseTypeStateEnum.INPUT_NULL);
		}
		else {
			i_courseType=courseTypeDao.queryCourseType(courseType);
			if(i_courseType==null) {
				int count=0;
				count=courseTypeDao.insertCourseType(courseType);
				if(count>0) {
					return new CourseTypeExecution(CourseTypeStateEnum.INSERT_PASS,courseType);
				}
				else {
					return new CourseTypeExecution(CourseTypeStateEnum.INSERT_ERRO);
				}
			}
			else {
				return new CourseTypeExecution(CourseTypeStateEnum.INSERT_ERRO);
			}
		}
	}

	@Transactional
	@Override
	public CourseTypeExecution deleteCourseType(CourseType courseType) throws RuntimeException {
		if(courseType==null) {
			return new CourseTypeExecution(CourseTypeStateEnum.INPUT_NULL);
		}
		else {
			boolean d_courseType=false;
			boolean dc_courseType=false;
			dc_courseType=courseTypeDao.deleteCourseTypeC(courseType.getCourseTypeId());
			if(dc_courseType) {
				d_courseType=courseTypeDao.deleteCourseType(courseType.getCourseTypeId());
				if(d_courseType) {
					return new CourseTypeExecution(CourseTypeStateEnum.DELETE_PASS,courseType);
				}
				else {
					return new CourseTypeExecution(CourseTypeStateEnum.DELETE_ERRO);
				}
			}
			else {
				return new CourseTypeExecution(CourseTypeStateEnum.DELETE_ERRO);
			}
		}
	}

	@Override
	public CourseTypeExecution updateCourseType(CourseType courseType) throws RuntimeException {
		if(courseType==null) {
			return new CourseTypeExecution(CourseTypeStateEnum.INPUT_NULL);
		}
		else {
			int u_courseType=0;
			u_courseType=courseTypeDao.updateCourseType(courseType);
			if(u_courseType>0) {
				return new CourseTypeExecution(CourseTypeStateEnum.UPDATE_PASS);
			}
			else {
				return new CourseTypeExecution(CourseTypeStateEnum.UPDATE_ERRO);
			}
		}
	}

	@Override
	public CourseTypeExecution showCourseTypeList(int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<CourseType> courseTypeList = null;
		courseTypeList = courseTypeDao.queryCourseTypeList(rowIndex, pageSize);
		int count = courseTypeDao.queryCourseTypeCount();
		CourseTypeExecution ce = new CourseTypeExecution();
		if (courseTypeList != null && courseTypeList.size() > 0) {
			ce.setState(CourseTypeStateEnum.GET_SECCESS.getState());
			ce.setCourseTypeList(courseTypeList);
			ce.setCount(count);
		} else {
			ce.setState(CourseTypeStateEnum.GET_FALSE.getState());
		}
		return ce;
	}



}
