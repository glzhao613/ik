package com.gz.ik.web.filetype;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.FileTypeExecution;
import com.gz.ik.dto.FilesExecution;
import com.gz.ik.entity.FileType;
import com.gz.ik.entity.Files;
import com.gz.ik.enums.FileTypeStateEnum;
import com.gz.ik.enums.FilesStateEnum;
import com.gz.ik.service.FileTypeService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/filetypeadmin")
public class FileTypeManagementController {

	@Autowired
	private FileTypeService fileTypeService;
	
	@RequestMapping(value = "/filetypelist", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private Map<String, Object> getFileTypeList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		FileTypeExecution ex=fileTypeService.queryFileTypeList(new FileType());
		if(ex.getState() == FileTypeStateEnum.QUERY_SECCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("filetypelist", ex.getEntityList());
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ex.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		FileType fileType=new FileType();
		if (request.getSession().getAttribute("byfiletypeid") != null) {
			request.getSession().removeAttribute("byfiletypeid");
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			FileTypeExecution ex = fileTypeService.getFileTypeList(fileType, pageIndex, pageSize);
			if (ex.getState() == FileTypeStateEnum.QUERY_SECCESS.getState()) {
				modelMap.put("list", ex.getEntityList());
				modelMap.put("count", (ex.getCount() - 1) / pageSize + 1);
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());

			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/delfiletype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> delFileType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int fileTypeId=(int)HttpServletRequestUtil.getInt(request, "filetypeid");
		FileType fileType=new FileType();
		fileType.setFileTypeId(fileTypeId);
		FileTypeExecution ex =fileTypeService.delFileType(fileType);
		if(ex.getState()==FileTypeStateEnum.DEL_SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ex.getStateInfo());
		}
		return modelMap;
	}
	
	
	@RequestMapping(value = "/setfiletypeid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByFileTypeId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int id = HttpServletRequestUtil.getInt(request, "filetypeid");
		if (id != -1) {
			request.getSession().setAttribute("byfiletypeid", id);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
	@RequestMapping(value = "/updatefiletype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateFileType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String fileTypeName=HttpServletRequestUtil.getString(request, "filetypename");
		Integer fileTypeId=(Integer)request.getSession().getAttribute("byfiletypeid");
		FileType fileType=new FileType();
		if(fileTypeName==null||fileTypeId==-1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入内容");
		}else {
			fileType.setFileTypeId(fileTypeId);
			fileType.setFileTypeName(fileTypeName);
			FileTypeExecution ex=fileTypeService.updateFileType(fileType);
			if(ex.getState()==FileTypeStateEnum.UPDATE_SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());
			}
		}
		return modelMap;
	}

	@RequestMapping(value = "/addfiletype", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addFileType(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String fileTypeName=(String)HttpServletRequestUtil.getString(request, "filetypename");
		FileType fileType=new FileType();
		if(fileTypeName==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入内容");
		}else {
			fileType.setFileTypeName(fileTypeName);
			FileTypeExecution ex =fileTypeService.addFileType(fileType);
			if(ex.getState()==FileTypeStateEnum.INSERT_SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());
			}
				
		}
		return modelMap;
	}
	
	

	
}
