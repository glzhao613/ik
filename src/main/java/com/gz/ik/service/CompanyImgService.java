package com.gz.ik.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.CompanyImgExecution;
import com.gz.ik.entity.CompanyImg;

public interface CompanyImgService {
	
	CompanyImgExecution querCheck(CompanyImg companyimg) throws RuntimeException;
	
	CompanyImgExecution querPagingCheck(Map<String,Object> pageMap) throws RuntimeException;
	
	CompanyImgExecution querAllCheck() throws RuntimeException;
	
	CompanyImgExecution addCheck(CompanyImg companyimg,CommonsMultipartFile img) throws RuntimeException;
	
	CompanyImgExecution updateCheck(CompanyImg companyimg) throws RuntimeException;
	
	CompanyImgExecution deleteCheck(CompanyImg companyimg) throws RuntimeException;
}
