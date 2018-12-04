package com.gz.ik.web.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.AdminDeleteExecution;
import com.gz.ik.dto.AdminExecution;
import com.gz.ik.dto.AdminRegisterExecution;
import com.gz.ik.dto.AdminUpdateExecution;
import com.gz.ik.dto.CourseTypeExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.Module;
import com.gz.ik.enums.AdminRegisterStateEnum;
import com.gz.ik.enums.AdminStateEnum;
import com.gz.ik.enums.AdminUpdateStateEnum;
import com.gz.ik.enums.CourseTypeStateEnum;
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
		String adminAct = HttpServletRequestUtil.getString(request, "adminaccount");
		String adminPwd = HttpServletRequestUtil.getString(request, "adminpwd");
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
		String adminAct = HttpServletRequestUtil.getString(request, "adminaccount");
		String adminPwd = HttpServletRequestUtil.getString(request, "adminpwd");
		int adminTy=HttpServletRequestUtil.getInt(request, "admintype");	
		int moduleId=HttpServletRequestUtil.getInt(request, "moduleid");
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
	
	@RequestMapping(value = "/adminupdate")
	@ResponseBody
	private Map<String, Object> adminUpdate(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Admin admin=new Admin();
		Module module=new Module();
		int adminId = HttpServletRequestUtil.getInt(request, "adminid");
		String adminAct = HttpServletRequestUtil.getString(request, "adminaccount");
		String adminPwd = HttpServletRequestUtil.getString(request, "adminpwd");
		Integer moduleId=HttpServletRequestUtil.getInt(request, "moduleid");
		String adminName=HttpServletRequestUtil.getString(request, "adminname");
		
		admin.setAdminId(adminId);
		module.setModuleId(moduleId);
		admin.setAdminAccount(adminAct);
		admin.setAdminPwd(adminPwd);
		admin.setAdminType(1);
		admin.setAdminModule(module);
		admin.setAdminName(adminName);
		
		AdminUpdateExecution ad = adminService.updateCheck(admin);
		if (ad.getState() == AdminUpdateStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若更新成功，则加入session中
			request.getSession().setAttribute("updateadmin", ad.getAdmin());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
			
		return modelMap;
	}
	
	@RequestMapping(value = "/admindelete", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> adminDelete(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Admin admin=new Admin();
		int adminId = HttpServletRequestUtil.getInt(request, "adminid");
		if (adminId >0) {
			admin.setAdminId(adminId);
			AdminDeleteExecution ad = adminService.deleteCheck(admin);
			if (ad.getState() == AdminStateEnum.PASS.getState()) {
				modelMap.put("success", 1);
				modelMap.put("adminname", ad.getAdmin().getAdminName());
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
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> showadminList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Admin admin=new Admin();
		if (request.getSession().getAttribute("bymoduleid") != null) {
			Module module=new Module();
			module.setModuleId((int)request.getSession().getAttribute("bycourseid"));
			admin.setAdminModule(module);
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			AdminExecution ae = adminService.showAdminList(admin,pageIndex, pageSize);
			if (ae.getState() == AdminStateEnum.GET_SECCESS.getState()) {
				modelMap.put("adminList", ae.getAdminlist());
				modelMap.put("count", (ae.getCount() - 1) / pageSize + 1);
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ae.getStateInfo());

			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByAdminId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int adminId = HttpServletRequestUtil.getInt(request, "adminid");
		if (adminId != -1) {
			request.getSession().setAttribute("byadminid", adminId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
}
