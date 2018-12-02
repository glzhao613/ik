package com.gz.ik.web.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gz.ik.dto.CourseTypeExecution;
import com.gz.ik.dto.TeacherExecution;
import com.gz.ik.entity.CourseType;
import com.gz.ik.entity.Teacher;
import com.gz.ik.enums.CourseTypeStateEnum;
import com.gz.ik.enums.TeacherStateEnum;
import com.gz.ik.service.TeacherService;
import com.gz.ik.util.HttpServletRequestUtil;


@Controller
@RequestMapping("/teacheradmin")
public class TeacherManagementController {
	
	@Autowired
	private TeacherService teacherService;
	

	@RequestMapping(value = "/teacherlist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getTeacherList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		TeacherExecution ce=teacherService.getTeacherList();
		if(ce.getState() == TeacherStateEnum.QUERY_SECCESS.getState()) {
			List<Teacher> teacherlist=ce.getTeacherList();
			modelMap.put("success", true);
			modelMap.put("teacherlist", teacherlist);
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/insertteacher", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> insertteacher(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Teacher teacher=new Teacher();
		String teacherName=HttpServletRequestUtil.getString(request, "teachername");
		String teacherDes=HttpServletRequestUtil.getString(request, "teacherdes");
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("timg");
		}
		
		teacher.setTeacherName(teacherName);
		teacher.setTeacherDes(teacherDes);
		
		TeacherExecution ad=teacherService.insertTeacher(teacher,img);
		if(ad.getState() == TeacherStateEnum.INSERT_PASS.getState()) {
			modelMap.put("success", true);
			// 若教师添加成功，则加入session中
			request.getSession().setAttribute("byteacher", ad.getTeacher());
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ad.getStateInfo());
		}
	return modelMap;
	}
	
	@RequestMapping(value = "/updateteacher", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateteacher(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Teacher teacher=new Teacher();
		String teacherName=HttpServletRequestUtil.getString(request, "teachername");
		String teacherDes=HttpServletRequestUtil.getString(request, "teacherdes");
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("timg");
		}
		
		teacher.setTeacherName(teacherName);
		teacher.setTeacherDes(teacherDes);
		
		TeacherExecution ad=teacherService.updateTeacher(teacher,img);
		if(ad.getState() == TeacherStateEnum.UPDATE_PASS.getState()) {
			modelMap.put("success", true);
			// 若教师修改成功，则加入session中
			request.getSession().setAttribute("byteacher", ad.getTeacher());
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ad.getStateInfo());
		}
	return modelMap;
	}
	
	@RequestMapping(value = "/deleteteacher", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deleteTeacher(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Teacher teacher=new Teacher();
		int teacherId=HttpServletRequestUtil.getInt(request, "teacherid");
		teacher.setTeacherId(teacherId);
		
		TeacherExecution ce=teacherService.deleteTeacher(teacher);
		if(ce.getState() == TeacherStateEnum.DELETE_PASS.getState()) {
			modelMap.put("success", true);
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
}