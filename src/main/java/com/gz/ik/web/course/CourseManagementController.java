package com.gz.ik.web.course;

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

import com.gz.ik.dto.CourseDeleteExecution;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseInsertExecution;
import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.Comments;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.CourseType;
import com.gz.ik.entity.Files;
import com.gz.ik.entity.Module;
import com.gz.ik.entity.Teacher;
import com.gz.ik.entity.UC;
import com.gz.ik.enums.CourseDeleteStateEnum;
import com.gz.ik.enums.CourseInsertStateEnum;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.CourseService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/courseadmin")
public class CourseManagementController {
	
	@Autowired
	private CourseService courseService;
	

	@RequestMapping(value = "/courselist", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private Map<String, Object> getCourseList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CourseExecution ce=courseService.getCourseList();
		if(ce.getState() == CourseStateEnum.QUERY_SECCESS.getState()) {
			List<Course> clist=ce.getCourseList();
			modelMap.put("success", true);
			modelMap.put("courselist", clist);
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}

	
	@RequestMapping(value = "/quercourse",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> quercourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Course course=new Course();
		int courseId=HttpServletRequestUtil.getInt(request, "courseid");
		
		course.setCourseId(courseId);
		
		CourseExecution ad=courseService.getCourse(course);
		
		if(ad.getState() == CourseStateEnum.QUERY_SECCESS.getState()) {
			modelMap.put("success", 1);
			// 若课程查询成功，则加入session中
			request.getSession().setAttribute("bycourse", ad.getCourse());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}

	@RequestMapping(value = "/insertcourse",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> insertcourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Course course=new Course();
		Teacher teacher=new Teacher();
		CourseType courseType=new CourseType();
		int courseTy=HttpServletRequestUtil.getInt(request, "coursety");
		int courseTeacher=HttpServletRequestUtil.getInt(request, "courseteacher");
		String courseName=HttpServletRequestUtil.getString(request, "coursename");
		String courseDes=HttpServletRequestUtil.getString(request, "coursedes");
		float coursePrice=HttpServletRequestUtil.getFloat(request, "courseprice");
		int courseHour=HttpServletRequestUtil.getInt(request, "coursehour");
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("cimg");
		}
		
		courseType.setCourseTypeId(courseTy);
		teacher.setTeacherId(courseTeacher);
		course.setCourseTeacher(teacher);
		course.setCourseName(courseName);
		course.setCourseDes(courseDes);
		course.setCoursePrice(coursePrice);
		course.setCourseHour(courseHour);
		
		course.setCourseType(courseType);
		CourseInsertExecution ad=courseService.insertCourse(course, img);
		if(ad.getState() == CourseInsertStateEnum.QUERY_SECCESS.getState()) {
			modelMap.put("success", true);
			// 若课程添加成功，则加入session中
			request.getSession().setAttribute("bycourse", ad.getCourse());
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		
		return modelMap;
	}

	@RequestMapping(value = "/deletecourse",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deletecourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Course course=new Course();
		Comments comments=new Comments();
		UC uc=new UC();
		Files files=new Files();
		
		int courseId=(int) request.getSession().getAttribute("bycourseid") ;
		
		course.setCourseId(courseId);
		comments.setCommentCourse(course);
		uc.setcId(courseId);
		files.setFileCourse(course);
		
		CourseDeleteExecution ad=courseService.deleteCourse(course,comments,uc,files);
		if(ad.getState() == CourseDeleteStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			modelMap.put("coursename", ad.getCourse().getCourseName());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "/updatecourse",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updatecourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Course course=new Course();
		Teacher teacher=new Teacher();
		CourseType courseType=new CourseType();
		int courseId=(int) request.getSession().getAttribute("bycourseid") ;
		int courseTy=HttpServletRequestUtil.getInt(request, "coursety");
		int courseTeacher=HttpServletRequestUtil.getInt(request, "courseteacher");
		String courseName=HttpServletRequestUtil.getString(request, "coursename");
		String courseDes=HttpServletRequestUtil.getString(request, "coursedes");
		float coursePrice=HttpServletRequestUtil.getFloat(request, "courseprice");
		int courseHour=HttpServletRequestUtil.getInt(request, "coursehour");
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("cimg");
		}
		
		courseType.setCourseTypeId(courseTy);
		teacher.setTeacherId(courseTeacher);
		course.setCourseTeacher(teacher);
		course.setCourseName(courseName);
		course.setCourseDes(courseDes);
		course.setCoursePrice(coursePrice);
		course.setCourseHour(courseHour);
		course.setCourseId(courseId);
		
		course.setCourseType(courseType);
		CourseExecution ad=courseService.updateCourse(course, img);
		if(ad.getState() == CourseStateEnum.UPDATE_PASS.getState()) {
			modelMap.put("success", true);
			// 若课程添加成功，则加入session中
			request.getSession().setAttribute("bycourse", ad.getCourse());
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByCourseId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int courseId = HttpServletRequestUtil.getInt(request, "courseid");
		if (courseId != -1) {
			request.getSession().setAttribute("bycourseid", courseId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> showCourseList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Course course=new Course();
		if (request.getSession().getAttribute("bycoursetypeid") != null) {
			CourseType courseType=new CourseType();
			courseType.setCourseTypeId((int)request.getSession().getAttribute("bycoursetypeid"));
			course.setCourseType(courseType);
		}
		if (request.getSession().getAttribute("byteacherid") != null) {
			Teacher teacher=new Teacher();
			teacher.setTeacherId((int)request.getSession().getAttribute("byteacherid"));
			course.setCourseTeacher(teacher);
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			CourseExecution ce = courseService.showCourseList(course,pageIndex, pageSize);
			if (ce.getState() == CourseStateEnum.GET_SECCESS.getState()) {
				modelMap.put("courseList", ce.getCourseList());
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
	
}
