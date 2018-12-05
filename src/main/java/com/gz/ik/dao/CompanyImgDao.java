package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import com.gz.ik.entity.Company;
import com.gz.ik.entity.CompanyImg;

public interface CompanyImgDao {
	//根据ID查询
	CompanyImg querCompanyImgById(CompanyImg companyimg);
	
	//分页管理查询
	List<CompanyImg> querCompanyImgPaging(Map<String,Object> pageMap);
	
	//查询所有
	List<CompanyImg> querAllCompanyImg();
	
	//根据图片ID查询公司
	Company querCompanyByCompanyImgId(int companyImgId);
	
	//插入一张图片
	int insertCompanyImg(CompanyImg companyimg);
	
	//更新图片信息
	int updateCompanyImg(CompanyImg companyimg);
	
	//删除图片
	boolean deleteCompanyImgById(int companyimgId);
}
