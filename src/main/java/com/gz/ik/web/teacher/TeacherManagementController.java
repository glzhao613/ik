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

import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.TeacherExecution;
import com.gz.ik.entity.Module;
import com.gz.ik.entity.Teacher;
import com.gz.ik.enums.CourseStateEnum;
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
		Teacher teacher=new Teacher();
		if (request.getSession().getAttribute("byteacherid") != null) {
			teacher.setTeacherId((int)request.getSession().getAttribute("byteacherid"));
		}		
		TeacherExecution ce=teacherService.getTeacherList(teacher);
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
		System.out.println(teacherDes);
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
		if(HttpServletRequestUtil.getString(request, "teachername")!=null) {
			String teacherName=HttpServletRequestUtil.getString(request, "teachername");
			teacher.setTeacherName(teacherName);
		}
		if(HttpServletRequestUtil.getString(request, "teacherdes")!=null) {
			String teacherDes=HttpServletRequestUtil.getString(request, "teacherdes");
			teacher.setTeacherDes(teacherDes);
		}
		if (request.getSession().getAttribute("byteacherid") != null) {
			teacher.setTeacherId((int)request.getSession().getAttribute("byteacherid"));
		}
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("timg");
		}
		
		
		
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
			modelMap.put("teachername", ce.getTeacher().getTeacherName());
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> showTeacherList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

		if ((pageIndex > -1) && (pageSize > -1)) {
			TeacherExecution ce = teacherService.showTeacherList(pageIndex, pageSize);
			if (ce.getState() == TeacherStateEnum.GET_SECCESS.getState()) {
				modelMap.put("teacherList", ce.getTeacherList());
				modelMap.put("count", (ce.getCount() - 1) / pageSize + 1);
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ce.getStateInfo());

			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByTeacherId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int teacherId = HttpServletRequestUtil.getInt(request, "teacherid");
		if (teacherId != -1) {
			request.getSession().setAttribute("byteacherid", teacherId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
}