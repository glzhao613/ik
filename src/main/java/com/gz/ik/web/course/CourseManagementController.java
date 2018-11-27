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

import com.gz.ik.dao.CourseDao;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.User;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.CourseService;
import com.gz.ik.service.UserService;
import com.gz.ik.util.CodeUtil;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/courseadmin")
public class CourseManagementController {
	
	@Autowired
	private CourseService courseService;
	

	@RequestMapping("/courselist")
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


	
}
