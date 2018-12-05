package com.gz.ik.web.files;

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
import com.gz.ik.dto.FileTypeExecution;
import com.gz.ik.dto.FilesExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.FileType;
import com.gz.ik.entity.Files;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.enums.FileTypeStateEnum;
import com.gz.ik.enums.FilesStateEnum;
import com.gz.ik.service.FileTypeService;
import com.gz.ik.service.FilesService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/filesadmin")
public class FilesManagementController {

	@Autowired
	private FilesService filesService;
	
	@RequestMapping(value = "/fileslist", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private Map<String, Object> getFilesList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		FilesExecution ex=filesService.queryFilesList(new Files());
		if(ex.getState() == FilesStateEnum.QUERY_SECCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("fileslist", ex.getEntityList());
			
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
		Files files = new Files();
		if (request.getSession().getAttribute("byfilesid") != null) {
			request.getSession().removeAttribute("byfilesid");
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			FilesExecution ex = filesService.getFilesList(files, pageIndex, pageSize);
			if (ex.getState() == FilesStateEnum.QUERY_SECCESS.getState()) {
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

	@RequestMapping(value = "/delfiles", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> delFiles(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int fileId = (int) HttpServletRequestUtil.getInt(request, "filesid");
		Files files = new Files();
		files.setFileId(fileId);
		FilesExecution ex = filesService.delFiles(files);
		if (ex.getState() == FilesStateEnum.DEL_SUCCESS.getState()) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ex.getStateInfo());
		}
		return modelMap;
	}

	@RequestMapping(value = "/setfilesid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByFilesId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int id = HttpServletRequestUtil.getInt(request, "filesid");
		if (id != -1) {
			request.getSession().setAttribute("byfilesid", id);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}

	@RequestMapping(value = "/updatefiles", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateFiles(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Integer fileId = (Integer) request.getSession().getAttribute("byfilesid");
		String fileName = HttpServletRequestUtil.getString(request, "filesname");
		String filePath = HttpServletRequestUtil.getString(request, "filespath");
		int fileTypeId = HttpServletRequestUtil.getInt(request, "filetypeid");
		
		Files files = new Files();
		files.setFileId(fileId);
		Files tFiles=filesService.queryFilesList(files).getEntityList().get(0);
		Integer tid=tFiles.getFileType().getFileTypeId();
		if (fileName != null || filePath != null||fileTypeId!=tid) {
			files.setFileName(fileName);
			files.setFilePath(filePath);
			FileType fileType = new FileType();
			fileType.setFileTypeId(fileTypeId);
			files.setFileType(fileType);
			FilesExecution ex = filesService.updateFiles(files);
			if (ex.getState() == FilesStateEnum.UPDATE_SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入要修改的信息");
		}
		return modelMap;
	}

	@RequestMapping(value = "/addfiles", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addFiles(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String fileName = HttpServletRequestUtil.getString(request, "filesname");
		String filePath = HttpServletRequestUtil.getString(request, "filespath");
		int courseId = HttpServletRequestUtil.getInt(request, "courseid");
		int fileTypeId = HttpServletRequestUtil.getInt(request, "filetypeid");
		Files files = new Files();
		if (fileName != null && filePath != null && courseId != -1 && fileTypeId != -1) {
			files.setFileName(fileName);
			files.setFilePath(filePath);
			Course course = new Course();
			course.setCourseId(courseId);
			files.setFileCourse(course);
			FileType fileType = new FileType();
			fileType.setFileTypeId(fileTypeId);
			files.setFileType(fileType);
			FilesExecution ex = filesService.addFiles(files);
			if (ex.getState() == FilesStateEnum.INSERT_SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "有空的输入");
		}
		return modelMap;
	}

}
