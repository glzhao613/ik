package com.gz.ik.web.companyimg;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gz.ik.dto.CompanyImgExecution;
import com.gz.ik.entity.CompanyImg;
import com.gz.ik.enums.CompanyImgStateEnum;
import com.gz.ik.service.CompanyImgService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/companyimgadm")
public class CompanyImgManagermentController {
	
	@Autowired
	private CompanyImgService companyimgservice;
	
	@RequestMapping(value="/companyimgman/{currentPage}",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> companyImgMan(@PathVariable Integer currentPage){
		Map<String, Object> PagingMap = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		if(currentPage == null){
			currentPage = 1;
		}
		PagingMap.put("currentPage", currentPage);
		int pageCount = 5;
		
		PagingMap.put("pageCount", pageCount);
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		CompanyImgExecution cie = companyimgservice.querPagingCheck(pageMap);
		if(cie.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			PagingMap.put("success", true);
			PagingMap.put("companyimglist", cie.getCompanyimgList());
		}else{
			PagingMap.put("success", false);
			PagingMap.put("errMsg", cie.getStateInfo());
		}
		
		CompanyImgExecution cie1 = companyimgservice.querAllCheck();
		if(cie1.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			PagingMap.put("companyimgtotal", cie1.getCompanyimgList().size());
		}
		return PagingMap;
	}
	
	@RequestMapping(value="/companyimgadd",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> addCompanyImg(HttpServletRequest request){
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		CompanyImg companyImg = new CompanyImg();
		
		String companyImgDes = HttpServletRequestUtil.getString(request, "companyimgdes");
		
		companyImg.setCompanyImgDes(companyImgDes);
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("companyimg");
		}
		CompanyImgExecution cie = companyimgservice.addCheck(companyImg, img);
		if(cie.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			CompanyImgMap.put("success", true);
			CompanyImgMap.put("companyImg", cie.getCompanyimg());
		}else{
			CompanyImgMap.put("success", false);
			CompanyImgMap.put("errMsg", cie.getStateInfo());
		}
		return CompanyImgMap;
	}
	
	@RequestMapping(value="/companyimgupdate",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> updateCompanyImg(HttpServletRequest request){
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		CompanyImg companyImg = new CompanyImg();
		
		String companyImgDes = HttpServletRequestUtil.getString(request, "companyimgdes");
		int companyImgId = (int) request.getSession().getAttribute("companyimgid");
		
		companyImg.setCompanyImgDes(companyImgDes);
		companyImg.setCompanyImgId(companyImgId);
		
		CompanyImgExecution cie = companyimgservice.updateCheck(companyImg);
		if(cie.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			CompanyImgMap.put("success", true);
			CompanyImgMap.put("companyImg", cie.getCompanyimg());
		}else{
			CompanyImgMap.put("success", false);
			CompanyImgMap.put("errMsg", cie.getStateInfo());
		}
		
		return CompanyImgMap;
	}
	
	@RequestMapping(value="/companyimgdelete",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> deleteCompanyImg(HttpServletRequest request){
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		CompanyImg companyImg = new CompanyImg();
		
		int companyImgId = HttpServletRequestUtil.getInt(request, "companyimgid");
		
		companyImg.setCompanyImgId(companyImgId);
		CompanyImgExecution cie = companyimgservice.deleteCheck(companyImg);
		if(cie.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			CompanyImgMap.put("success", true);
			CompanyImgMap.put("companyImg", cie.getStateInfo());
		}else{
			CompanyImgMap.put("success", false);
			CompanyImgMap.put("errMsg", "公司正在使用该图片，无法删除！");
		}
		
		return CompanyImgMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByCompanyImgId(HttpServletRequest request) {
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		
		int companyImgId = HttpServletRequestUtil.getInt(request, "companyimgid");
		
		CompanyImg companyImg = new CompanyImg();
		companyImg.setCompanyImgId(companyImgId);
		if (companyImgId != -1) {
			CompanyImgExecution cie = companyimgservice.querCheck(companyImg);
			CompanyImg t_companyImg = cie.getCompanyimg();
			request.getSession().setAttribute("companyimgid", t_companyImg.getCompanyImgId());
			request.getSession().setAttribute("updatecompanyimg", t_companyImg);
			CompanyImgMap.put("success", true);
		} else {
			CompanyImgMap.put("success", false);
		}
		return CompanyImgMap;
	}
	
	@RequestMapping(value = "/setupdateid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> setByComganyImgId(HttpServletRequest request) {
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		
		int companyImgId = (int) request.getSession().getAttribute("companyimgid");
		
		CompanyImg companyImg = new CompanyImg();
		companyImg.setCompanyImgId(companyImgId);
		if (companyImgId != -1) {
			CompanyImgExecution cie = companyimgservice.querCheck(companyImg);
			CompanyImg t_companyImg = cie.getCompanyimg();
			CompanyImgMap.put("success", true);
			CompanyImgMap.put("updatecompanyimg", t_companyImg);
		} else {
			CompanyImgMap.put("success", false);
		}
		return CompanyImgMap;
	}
	
	@RequestMapping(value = "/companyimg", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> companyImg(HttpServletRequest request) {
		Map<String, Object> CompanyImgMap = new HashMap<String, Object>();
		CompanyImgExecution cie = companyimgservice.querAllCheck();
		if(cie.getState() == CompanyImgStateEnum.SUCCESS.getState()){
			CompanyImgMap.put("companyimglist", cie.getCompanyimgList());
		}
		
		return CompanyImgMap;
	}
}
