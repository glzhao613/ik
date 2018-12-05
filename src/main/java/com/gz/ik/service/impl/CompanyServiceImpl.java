package com.gz.ik.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.CompanyDao;
import com.gz.ik.dto.CompanyExecution;
import com.gz.ik.entity.Company;
import com.gz.ik.enums.CompanyStateEnum;
import com.gz.ik.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companydao;

	@Override
	public CompanyExecution querCheck(Company company) throws RuntimeException {
		Company t_company = null;
		if(company == null){
			return new CompanyExecution(CompanyStateEnum.NULL_INPUT);
		}else{
			t_company = companydao.querCompany(company);
			if(t_company != null){
				return new CompanyExecution(CompanyStateEnum.SUCCESS,t_company);
			}else{
				return new CompanyExecution(CompanyStateEnum.FAILED);
			}
		}
	}

	@Override
	public CompanyExecution querPagingCheck(Map<String, Object> pageMap) throws RuntimeException {
		return new CompanyExecution(CompanyStateEnum.SUCCESS,companydao.querCompanyPaging(pageMap));
	}
	
	@Override
	public CompanyExecution querAllCheck() throws RuntimeException {
		return new CompanyExecution(CompanyStateEnum.SUCCESS,companydao.querAllCompany());
	}

	@Override
	public CompanyExecution addCheck(Company company) throws RuntimeException {
		if(company.getCompanyName() == null || company.getCompanyTel() == null || company.getCompanyEmail() == null || company.getCompanyAddress() == null || company.getCompanyDes() == null){
			return new CompanyExecution(CompanyStateEnum.NULL_INPUT);
		}else{
			Company t_company = companydao.querCompany(company);
			if(t_company !=null){
				return new CompanyExecution(CompanyStateEnum.ID_ERROR);
			}else{
				int count = companydao.insertCompany(company);
				if(count > 0){
					return new CompanyExecution(CompanyStateEnum.SUCCESS);
				}else{
					return new CompanyExecution(CompanyStateEnum.FAILED);
				}
			}
		}
	}

	@Override
	public CompanyExecution updateCheck(Company company) throws RuntimeException {
		if(company == null){
			return new CompanyExecution(CompanyStateEnum.NULL_INPUT);
		}else{
			Company t_company = companydao.querCompany(company);
			if(t_company == null){
				return new CompanyExecution(CompanyStateEnum.ID_ERROR);
			}else{
				int count = companydao.updateCompany(company);
				if(count > 0){
					return new CompanyExecution(CompanyStateEnum.SUCCESS);
				}else{
					return new CompanyExecution(CompanyStateEnum.FAILED);
				}
			}
		}
	}

	@Override
	public CompanyExecution deleteCheck(Company company) throws RuntimeException {
		if(company == null){
			return new CompanyExecution(CompanyStateEnum.NULL_INPUT);
		}else{
			Company t_company = companydao.querCompany(company);
			if(t_company == null){
				return new CompanyExecution(CompanyStateEnum.ID_ERROR);
			}else{
				boolean index = companydao.deleteCompany(company);
				if(index){
					return new CompanyExecution(CompanyStateEnum.SUCCESS);
				}else{
					return new CompanyExecution(CompanyStateEnum.FAILED);
				}
			}
		}
	}

}
