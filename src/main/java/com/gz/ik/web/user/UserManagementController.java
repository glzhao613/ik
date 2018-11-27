package com.gz.ik.web.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.User;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.UserService;
import com.gz.ik.util.CodeUtil;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/useradmin")
public class UserManagementController {
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> userLogin(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "kaptcha");
		if (!CodeUtil.checkVerifyCode(request, verifyCodeActual)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的验证码");
		} else {

			String userAct = HttpServletRequestUtil.getString(request, "userAccount");
			String userPwd = HttpServletRequestUtil.getString(request, "userPwd");
			if (userAct != null && userPwd != null) {
				user.setUserAccount(userAct);
				user.setUserPwd(userPwd);
				UserExecution ue = userService.loginCheck(user);
				if (ue.getState() == UserStateEnum.PASS.getState()) {
					modelMap.put("success", true);
					// 若密码验证通过，则加入session中
					request.getSession().setAttribute("loginuser", ue.getUser());

				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ue.getStateInfo());
				}

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请输入信息");
			}

		}

		return modelMap;
	}
	

	@RequestMapping(value = "/userupdatapwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updataUserPwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		User sessionUser = (User)(request.getSession().getAttribute("loginuser"));
		String userPwd = HttpServletRequestUtil.getString(request, "userpwd");
		String newPwd = HttpServletRequestUtil.getString(request, "newpwd");
		if (userPwd != null&& newPwd!=null) {
			user.setUserAccount(sessionUser.getUserAccount());
			user.setUserPwd(userPwd);
			UserExecution ue = userService.updataUserPwd(user, newPwd);
			if (ue.getState() == UserStateEnum.UPDATAPWD_SUCCESS.getState()) {
				modelMap.put("success", true);
				request.getSession().setAttribute("loginuser", null);

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入信息");
		}


		return modelMap;
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> userAdd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println("");
		User user = new User();
		String account = HttpServletRequestUtil.getString(request, "useraccount");
		String pwd = HttpServletRequestUtil.getString(request, "userpwd");
		int courseId=HttpServletRequestUtil.getInt(request, "course");
		System.out.println(account+pwd+courseId+"");
		user.setUserAccount(account);
		user.setUserPwd(pwd);
		Course course=new Course();
		course.setCourseId(courseId);
		List<Course> list=new ArrayList<>();
		list.add(course);
		user.setCourseList(list);
		UserExecution ue= userService.userAdd(user);
		if (ue.getState() == UserStateEnum.REG_SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("useraccount", ue.getUser().getUserAccount());

		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ue.getStateInfo());
			
		}
		return modelMap;
	}
	
}
