package com.gz.ik.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.ModuleAddExecution;
import com.gz.ik.dto.ModuleDeleteExecution;
import com.gz.ik.dto.ModuleQuerExecution;
import com.gz.ik.dto.ModuleUpdateExecution;
import com.gz.ik.entity.Module;
import com.gz.ik.enums.ModuleAddStateEnum;
import com.gz.ik.enums.ModuleDeleteStateEnum;
import com.gz.ik.enums.ModuleQuerStateEnum;
import com.gz.ik.enums.ModuleUpdateStateEnum;
import com.gz.ik.service.ModuleService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/modulead")
public class ModuleManagermentController {

	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value="/modulequer",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> moduleQuer(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Module module=new Module();
		String moduleName=HttpServletRequestUtil.getString(request, "modulename");
		module.setModuleName(moduleName);
		ModuleQuerExecution ad=moduleService.QuerCheck(module);
		if (ad.getState() == ModuleQuerStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若查询成功，则加入session中
			request.getSession().setAttribute("quermodule", ad.getModule());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
	
	
	@RequestMapping(value="/moduleadd")
	@ResponseBody
	private Map<String, Object> moduleAdd(HttpServletRequest request){
		System.out.println(111);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Module module=new Module();
		String moduleName=HttpServletRequestUtil.getString(request, "modulename");
		String moduleManageUrl=HttpServletRequestUtil.getString(request, "modulemanageurl");
		module.setModuleName(moduleName);
		module.setModuleManageUrl(moduleManageUrl);
		ModuleAddExecution ad=moduleService.AddCheck(module);
		if (ad.getState() == ModuleAddStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若添加成功，则加入session中
			request.getSession().setAttribute("addmodule", ad.getModule());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/moduleupdate")
	@ResponseBody
	private Map<String, Object> moduleUpdate(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Module module=new Module();
		int moduleId=HttpServletRequestUtil.getInt(request, "moduleid");
		String moduleName=HttpServletRequestUtil.getString(request, "modulename");
		String moduleUrl=HttpServletRequestUtil.getString(request, "moduleurl");
		String moduleManageUrl=HttpServletRequestUtil.getString(request, "modulemanageurl");
		module.setModuleId(moduleId);
		module.setModuleName(moduleName);
		module.setModuleUrl(moduleUrl);
		module.setModuleManageUrl(moduleManageUrl);
		ModuleUpdateExecution ad=moduleService.UpdateCheck(module);
		if(ad.getState() == ModuleUpdateStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若更新成功，则加入session中
			request.getSession().setAttribute("updatemodule", ad.getModule());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/moduledelete")
	@ResponseBody
	private Map<String, Object> moduleDelete(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Module module=new Module();
		String moduleName=HttpServletRequestUtil.getString(request, "modulename");
		module.setModuleName(moduleName);
		ModuleDeleteExecution ad=moduleService.DeleteCheck(module);
		if(ad.getState() == ModuleDeleteStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
}
