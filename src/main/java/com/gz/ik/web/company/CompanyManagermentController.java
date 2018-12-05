package com.gz.ik.web.company;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.CompanyExecution;
import com.gz.ik.entity.Company;
import com.gz.ik.entity.CompanyImg;
import com.gz.ik.enums.CompanyStateEnum;
import com.gz.ik.service.CompanyService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/companyadm")
public class CompanyManagermentController {
	
	@Autowired
	private CompanyService companyservice;
	
	@RequestMapping(value="/companyman/{currentPage}",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> companyMan(@PathVariable Integer currentPage){
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
		CompanyExecution ce = companyservice.querPagingCheck(pageMap);
		if(ce.getState() == CompanyStateEnum.SUCCESS.getState()){
			PagingMap.put("success", true);
			PagingMap.put("companylist", ce.getCompanyList());
		}else{
			PagingMap.put("success", false);
			PagingMap.put("errMsg", ce.getStateInfo());
		}
		
		CompanyExecution ce1 = companyservice.querAllCheck();
		if(ce1.getState() == CompanyStateEnum.SUCCESS.getState()){
			PagingMap.put("companytotal", ce1.getCompanyList().size());
		}
		return PagingMap;
	}
	
	@RequestMapping(value="/companyadd",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> addCompany(HttpServletRequest request){
		Map<String, Object> CompanyMap = new HashMap<String, Object>();
		Company company = new Company();
		CompanyImg companyImg = new CompanyImg();
		
		int companyImgId = HttpServletRequestUtil.getInt(request, "companyimg");
		
		companyImg.setCompanyImgId(companyImgId);
		
		String companyName = HttpServletRequestUtil.getString(request, "companyname");
		String companyTel = HttpServletRequestUtil.getString(request, "companytel");
		String companyEmail = HttpServletRequestUtil.getString(request, "companyemail");
		String companyAddress = HttpServletRequestUtil.getString(request, "companyaddress");
		String companyDes = HttpServletRequestUtil.getString(request, "companydes");
		
		company.setCompanyImg(companyImg);
		company.setCompanyName(companyName);
		company.setCompanyTel(companyTel);
		company.setCompanyEmail(companyEmail);
		company.setCompanyAddress(companyAddress);
		company.setCompanyDes(companyDes);
		
		CompanyExecution ce = companyservice.addCheck(company);
		if(ce.getState() == CompanyStateEnum.SUCCESS.getState()){
			CompanyMap.put("success", true);
			CompanyMap.put("company", ce.getCompany());
		}else{
			CompanyMap.put("success", false);
			CompanyMap.put("errMsg", ce.getStateInfo());
		}
		return CompanyMap;
	}
	
	@RequestMapping(value="/companyupdate",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> updateCompany(HttpServletRequest request){
		Map<String, Object> CompanyMap = new HashMap<String, Object>();
		Company company = new Company();
		CompanyImg companyImg = new CompanyImg();
		
		int companyId = (int) request.getSession().getAttribute("companyid");
		
		company.setCompanyId(companyId);
		
		int companyImgId = HttpServletRequestUtil.getInt(request, "companyimg");
		
		companyImg.setCompanyImgId(companyImgId);
		
		String companyName = HttpServletRequestUtil.getString(request, "companyname");
		String companyTel = HttpServletRequestUtil.getString(request, "companytel");
		String companyEmail = HttpServletRequestUtil.getString(request, "companyemail");
		String companyAddress = HttpServletRequestUtil.getString(request, "companyaddress");
		String companyDes = HttpServletRequestUtil.getString(request, "companydes");
		
		company.setCompanyImg(companyImg);
		company.setCompanyName(companyName);
		company.setCompanyTel(companyTel);
		company.setCompanyEmail(companyEmail);
		company.setCompanyAddress(companyAddress);
		company.setCompanyDes(companyDes);
		
		CompanyExecution ce = companyservice.updateCheck(company);
		if(ce.getState() == CompanyStateEnum.SUCCESS.getState()){
			CompanyMap.put("success", true);
			CompanyMap.put("company", ce.getCompany());
		}else{
			CompanyMap.put("success", false);
			CompanyMap.put("errMsg", ce.getStateInfo());
		}
		return CompanyMap;
	}
	
	@RequestMapping(value="/companydelete",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> deleteCompany(HttpServletRequest request){
		Map<String, Object> CompanyMap = new HashMap<String, Object>();
		Company company = new Company();
		
		int companyId = HttpServletRequestUtil.getInt(request, "companyid");
		
		company.setCompanyId(companyId);
		CompanyExecution ce = companyservice.deleteCheck(company);
		if(ce.getState() == CompanyStateEnum.SUCCESS.getState()){
			CompanyMap.put("success", true);
			CompanyMap.put("companyImg", ce.getStateInfo());
		}else{
			CompanyMap.put("success", false);
			CompanyMap.put("errMsg", ce.getStateInfo());
		}
		return CompanyMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByCompanyId(HttpServletRequest request) {
		Map<String, Object> CompanyMap = new HashMap<String, Object>();
		
		int companyId = HttpServletRequestUtil.getInt(request, "companyid");
		
		Company company = new Company();
		company.setCompanyId(companyId);
		if (companyId != -1) {
			CompanyExecution ce = companyservice.querCheck(company);
			Company t_company = ce.getCompany();
			request.getSession().setAttribute("companyid", t_company.getCompanyId());
			request.getSession().setAttribute("updatecompany", t_company);
			CompanyMap.put("success", true);
		} else {
			CompanyMap.put("success", false);
		}
		return CompanyMap;
	}
	
	@RequestMapping(value = "/setupdateid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> setByComganyId(HttpServletRequest request) {
		Map<String, Object> CompanyMap = new HashMap<String, Object>();
		
		int companyId = (int) request.getSession().getAttribute("companyid");
		
		Company company = new Company();
		company.setCompanyId(companyId);
		if (companyId != -1) {
			CompanyExecution ce = companyservice.querCheck(company);
			Company t_company = ce.getCompany();
			CompanyMap.put("success", true);
			CompanyMap.put("updatecompany", t_company);
		} else {
			CompanyMap.put("success", false);
		}
		return CompanyMap;
	}
	
}
