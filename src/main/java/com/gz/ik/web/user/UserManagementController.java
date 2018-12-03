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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
		User sessionUser = (User) (request.getSession().getAttribute("loginuser"));
		String userPwd = HttpServletRequestUtil.getString(request, "userpwd");
		String newPwd = HttpServletRequestUtil.getString(request, "newpwd");
		if (userPwd != null && newPwd != null) {
			user.setUserAccount(sessionUser.getUserAccount());
			user.setUserPwd(userPwd);
			UserExecution ue = userService.updataUserPwd(user, newPwd);
			if (ue.getState() == UserStateEnum.UPDATAPWD_SUCCESS.getState()) {
				modelMap.put("success", true);
				request.getSession().removeAttribute("loginuser");

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
		User user = new User();
		String account = HttpServletRequestUtil.getString(request, "useraccount");
		String pwd = HttpServletRequestUtil.getString(request, "userpwd");
		int courseId = HttpServletRequestUtil.getInt(request, "course");
		// System.out.println(account + pwd + courseId + "");
		user.setUserAccount(account);
		user.setUserPwd(pwd);
		Course course = new Course();
		course.setCourseId(courseId);
		List<Course> list = new ArrayList<>();
		list.add(course);
		user.setCourseList(list);
		UserExecution ue = userService.userAdd(user);
		if (ue.getState() == UserStateEnum.REG_SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("useraccount", ue.getUser().getUserAccount());

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ue.getStateInfo());

		}
		return modelMap;
	}

	@RequestMapping(value = "/deluser", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> userDel(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String account = HttpServletRequestUtil.getString(request, "useraccount");
		if (account == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", UserStateEnum.NULL_INPUT.getStateInfo());
		} else {
			UserExecution ue = userService.userDel(account);
			if (ue.getState() == UserStateEnum.DELUSER_SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("useraccount", ue.getUser().getUserAccount());

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());

			}

		}
		return modelMap;
	}

	@RequestMapping(value = "/userupdatainfo", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updataUserInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String name = HttpServletRequestUtil.getString(request, "name");
		String tel = HttpServletRequestUtil.getString(request, "tel");
		String des = HttpServletRequestUtil.getString(request, "des");
		User user = null;

		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("uImg");
		}
		if (name != null || tel != null || des != tel || img != null) {
			user = new User();
			User loginUser = (User) request.getSession().getAttribute("loginuser");
			user.setUserAccount(loginUser.getUserAccount());
			user.setUserName(name);
			user.setUserTel(tel);
			user.setUserDes(des);
			UserExecution ue = userService.updataUserInfo(user, img);
			if (ue.getState() == UserStateEnum.UPDATAINFO_SUCCESS.getState()) {
				modelMap.put("success", true);
				request.getSession().setAttribute("loginuser", ue.getUser());

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());

			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有输入要修改的信息");
		}

		return modelMap;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getUserList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if (request.getSession().getAttribute("byuserid") != null) {
			request.getSession().removeAttribute("byuserid");
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			UserExecution ue = userService.getUserList(pageIndex, pageSize);
			if (ue.getState() == UserStateEnum.GET_SECCESS.getState()) {
				modelMap.put("userList", ue.getUserList());
				modelMap.put("count", (ue.getCount() - 1) / pageSize + 1);
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());

			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}

	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByUserId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int userId = HttpServletRequestUtil.getInt(request, "userid");
		if (userId != -1) {
			request.getSession().setAttribute("byuserid", userId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}

	@RequestMapping(value = "/usercourse", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getUserCourseList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Course> cList = null;
		int userId = (int) request.getSession().getAttribute("byuserid");

		cList = userService.queryCourseByUserId(userId);
		if (cList == null || cList.size() <= 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "获取数据失败");
		} else {
			modelMap.put("success", true);
			modelMap.put("cList", cList);
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/addusercourse", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addUserCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		int userId = (int)request.getSession().getAttribute("byuserid");
		int courseId = HttpServletRequestUtil.getInt(request, "courseid");
		user.setUserId(userId);
		Course course = new Course();
		course.setCourseId(courseId);
		List<Course> list = new ArrayList<>();
		list.add(course);
		user.setCourseList(list);
		UserExecution ue = userService.addUserCourse(user);
		if (ue.getState() == UserStateEnum.ADDCOURSE_SUCCESS.getState()) {
			modelMap.put("success", true);

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ue.getStateInfo());

		}
		return modelMap;
	}
	@RequestMapping(value = "/delusercourse", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> delUserCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		int userId = (int)request.getSession().getAttribute("byuserid");
		int courseId = HttpServletRequestUtil.getInt(request, "courseid");
		user.setUserId(userId);
		Course course = new Course();
		course.setCourseId(courseId);
		List<Course> list = new ArrayList<>();
		list.add(course);
		user.setCourseList(list);
		UserExecution ue = userService.delUserCourse(user);
		if (ue.getState() == UserStateEnum.DELCOURSE_SUCCESS.getState()) {
			modelMap.put("success", true);

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ue.getStateInfo());

		}
		return modelMap;
	}
}
