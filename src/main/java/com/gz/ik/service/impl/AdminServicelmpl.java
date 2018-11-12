package com.gz.ik.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.AdminDao;
import com.gz.ik.dto.AdminExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.enums.AdminStateEnum;
import com.gz.ik.service.AdminService;


@Service
public class AdminServicelmpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public AdminExecution loginCheck(Admin admin) throws RuntimeException {
		
		Admin t_admin=null;
		if(admin==null) {
			return new AdminExecution(AdminStateEnum.NULL_INPUT);
		}
		else {
			t_admin=adminDao.queryadmin(admin.getAdminAccount());
			if(t_admin==null) {
				return new AdminExecution(AdminStateEnum.NO_ACCOUNT);
			}
			else {
				if(!(admin.getAdminPwd().equals(t_admin.getAdminPwd()))) {
					return new AdminExecution(AdminStateEnum.PWD_ERROR);
				}
				else if(admin.getAdminType()==0) {
					return new AdminExecution(AdminStateEnum.PASS_SUPER);
				}
			}
			
		}
		return new AdminExecution(AdminStateEnum.PASS,t_admin);
	}
	
}
