package com.gz.ik.web.course;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dao.CourseDao;
import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.User;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.UserService;
import com.gz.ik.util.CodeUtil;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/courseadmin")
public class CourseManagementController {
	
	@Autowired
	private CourseDao courseDao;
	

	@RequestMapping("/courselist")
	@ResponseBody
	private Map<String, Object> updataUserPwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		return modelMap;
	}


	
}
