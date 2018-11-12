package com.gz.ik.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.UserExecution;
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


	
}
