package com.gz.ik.web.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.AdminExecution;
import com.gz.ik.dto.AdminRegisterExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.Module;
import com.gz.ik.enums.AdminRegisterStateEnum;
import com.gz.ik.enums.AdminStateEnum;
import com.gz.ik.service.AdminService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/adminad")
public class AdminManagementController {
	@Autowired
	private AdminService adminService;
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> adminLogin(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Admin admin=new Admin();
		String adminAct = HttpServletRequestUtil.getString(request, "adminAccount");
		String adminPwd = HttpServletRequestUtil.getString(request, "adminPwd");
		if (adminAct != null && adminPwd != null) {
			admin.setAdminAccount(adminAct);
			admin.setAdminPwd(adminPwd);
			AdminExecution ad = adminService.loginCheck(admin);
			if (ad.getState() == AdminStateEnum.PASS.getState()) {
				modelMap.put("success", 1);
				// 若密码验证通过，则加入session中
				request.getSession().setAttribute("loginadmin", ad.getAdmin());

			} 
			else if(ad.getState()==AdminStateEnum.PASS_SUPER.getState()) {
				modelMap.put("success",0);
				request.getSession().setAttribute("loginsuperadmin", ad.getAdmin());
			}
			
			else {
				modelMap.put("success", -1);
				modelMap.put("errMsg", ad.getStateInfo());
			}

		}else {
			modelMap.put("success", -2);
			modelMap.put("errMsg", "请输入信息");
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/adminregister")
	@ResponseBody
	private Map<String, Object> adminRegister(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Admin admin=new Admin();
		Module module=new Module();
		String adminAct = HttpServletRequestUtil.getString(request, "adminAccount");
		String adminPwd = HttpServletRequestUtil.getString(request, "adminPwd");
		Integer adminTy=HttpServletRequestUtil.getInt(request, "adminType");
		Integer moduleId=HttpServletRequestUtil.getInt(request, "moduleId");
		if (adminAct != null && adminPwd != null && adminTy !=-1) {
			admin.setAdminAccount(adminAct);
			admin.setAdminPwd(adminPwd);
			admin.setAdminType(adminTy);
			module.setModuleId(moduleId);
			admin.setAdminModule(module);
			AdminRegisterExecution ad = adminService.registerCheck(admin);
			if (ad.getState() == AdminRegisterStateEnum.PASS.getState()) {
				modelMap.put("success", 1);
				// 若注册通过，则加入session中
				request.getSession().setAttribute("registeradmin", ad.getAdmin());

			} 
			else {
				modelMap.put("success", -1);
				modelMap.put("errMsg", ad.getStateInfo());
			}

		}else {
			modelMap.put("success", -2);
			modelMap.put("errMsg", "请输入信息");
		}
		return modelMap;
	}
}
