package com.gz.ik.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.CompanyExecution;
import com.gz.ik.entity.Company;

public interface CompanyService {
	
	CompanyExecution querCheck(Company company) throws RuntimeException;
	
	CompanyExecution querPagingCheck(Map<String,Object> pageMap) throws RuntimeException;
	
	CompanyExecution querAllCheck() throws  RuntimeException;
	
	CompanyExecution addCheck(Company company) throws RuntimeException;
	
	CompanyExecution updateCheck(Company company) throws RuntimeException;
	
	CompanyExecution deleteCheck(Company company) throws RuntimeException;
}
