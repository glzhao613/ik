package com.gz.ik.web.coursetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseTypeExecution;

import com.gz.ik.entity.CourseType;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.enums.CourseTypeStateEnum;

import com.gz.ik.service.CourseTypeService;
import com.gz.ik.util.HttpServletRequestUtil;


@Controller
@RequestMapping("/coursetypeadmin")
public class CourseTypeManagementController {
	
	@Autowired
	private CourseTypeService courseTypeService;
	

	@RequestMapping(value = "/coursetypelist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getCourseTypeList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CourseTypeExecution ce=courseTypeService.getCourseTypeList();
		if(ce.getState() == CourseTypeStateEnum.QUERY_SECCESS.getState()) {
			List<CourseType> ctylist=ce.getCourseTypeList();
			modelMap.put("success", true);
			modelMap.put("coursetypelist", ctylist);
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/insertcoursetype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> insertCourseType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CourseType courseType=new CourseType();
		String courseTypeName=HttpServletRequestUtil.getString(request, "coursetypename");
		courseType.setCourseTypeName(courseTypeName);
		
		CourseTypeExecution ce=courseTypeService.insertCourseType(courseType);
		if(ce.getState() == CourseTypeStateEnum.INSERT_PASS.getState()) {
			modelMap.put("success", true);
			modelMap.put("bycourse", ce.getCourseType());
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/updatecoursetype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateCourseType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CourseType courseType=new CourseType();
		String courseTypeName=HttpServletRequestUtil.getString(request, "coursetypename");
		courseType.setCourseTypeName(courseTypeName);
		
		CourseTypeExecution ce=courseTypeService.updateCourseType(courseType);
		if(ce.getState() == CourseTypeStateEnum.UPDATE_PASS.getState()) {
			modelMap.put("success", true);
			modelMap.put("bycourse", ce.getCourseType());
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/deletecoursetype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deleteCourseType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CourseType courseType=new CourseType();
		int courseTypeId=HttpServletRequestUtil.getInt(request, "coursetypeid");
		courseType.setCourseTypeId(courseTypeId);
		
		CourseTypeExecution ce=courseTypeService.deleteCourseType(courseType);
		if(ce.getState() == CourseTypeStateEnum.DELETE_PASS.getState()) {
			modelMap.put("success", true);
			modelMap.put("coursetypename", ce.getCourseType().getCourseTypeName());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
			
		}

		return modelMap;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> showCourseTypeList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

		if ((pageIndex > -1) && (pageSize > -1)) {
			CourseTypeExecution ce = courseTypeService.showCourseTypeList(pageIndex, pageSize);
			if (ce.getState() == CourseTypeStateEnum.GET_SECCESS.getState()) {
				modelMap.put("coursetypeList", ce.getCourseTypeList());
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
	private Map<String, Object> setByCourseTypeId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int courseTypeId = HttpServletRequestUtil.getInt(request, "coursetypeid");
		if (courseTypeId != -1) {
			request.getSession().setAttribute("bycoursetypeid", courseTypeId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
}