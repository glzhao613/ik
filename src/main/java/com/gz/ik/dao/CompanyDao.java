package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import com.gz.ik.entity.Company;

public interface CompanyDao {
	//查询一条公司信息
	Company querCompany(Company company);
	
	//查询所有公司信息
	List<Company> querAllCompany();
	
	//查询分页管理
	List<Company> querCompanyPaging(Map<String,Object> pageMap);
	
	//插入一条公司信息
	int insertCompany(Company company);
	
	//更新一条公司信息
	int updateCompany(Company company);
	
	//删除一条公司信息
	boolean deleteCompany(Company company);
}
