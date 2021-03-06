package com.gz.ik.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.AdminDao;
import com.gz.ik.dto.AdminDeleteExecution;
import com.gz.ik.dto.AdminExecution;
import com.gz.ik.dto.AdminRegisterExecution;
import com.gz.ik.dto.AdminUpdateExecution;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.Course;
import com.gz.ik.enums.AdminDeleteStateEnum;
import com.gz.ik.enums.AdminRegisterStateEnum;
import com.gz.ik.enums.AdminStateEnum;
import com.gz.ik.enums.AdminUpdateStateEnum;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.service.AdminService;
import com.gz.ik.util.PageCalculator;


@Service
public class AdminServicelmpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public AdminExecution loginCheck(Admin admin) throws RuntimeException {
		
		Admin t_admin=null;
		Admin a_admin=null;
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
				a_admin=adminDao.querAdminModuleUrl(admin);
				if(a_admin!=null) {
					return new AdminExecution(AdminStateEnum.PASS,a_admin);
				}
				else {
					return new AdminExecution(AdminStateEnum.GET_FALSE);
				}
			}
			
		}
	}

	@Override
	public AdminRegisterExecution registerCheck(Admin admin) throws RuntimeException {
		Admin r_admin=null;
		if(admin.getAdminType()==0) {
			return new AdminRegisterExecution(AdminRegisterStateEnum.NULL_PERMISS);
		}
		else {
			r_admin=adminDao.queryadmin(admin.getAdminAccount());
			if(r_admin==null) {
				int count=adminDao.insertadmin(admin);
				if(count>0)
					return new AdminRegisterExecution(AdminRegisterStateEnum.PASS,admin);
				else {
					return new AdminRegisterExecution(AdminRegisterStateEnum.PMISS);
				}
			}
			else {
				return new AdminRegisterExecution(AdminRegisterStateEnum.NO_ACCOUNT);
			}
		}
		
	}

	@Override
	public AdminUpdateExecution updateCheck(Admin admin) throws RuntimeException {
		int count=adminDao.updateadmin(admin);
		if(count>0) {
			return new AdminUpdateExecution(AdminUpdateStateEnum.PASS,admin);
		}
		else {
			return new AdminUpdateExecution(AdminUpdateStateEnum.ERROR);
		}
	}

	@Override
	public AdminDeleteExecution deleteCheck(Admin admin) throws RuntimeException {
		boolean yn=false;

		
		yn = adminDao.deleteadmin(admin.getAdminId());
		if (yn) {
			return new AdminDeleteExecution(AdminDeleteStateEnum.PASS, admin);
		} else {
			return new AdminDeleteExecution(AdminDeleteStateEnum.ERROR);
		}
		
	}

	@Override
	public AdminExecution showAdminList(Admin admin,int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Admin> adminList = null;
		adminList = adminDao.queryAdminList(admin,rowIndex, pageSize);
		int count = adminDao.queryAdminCount();
		AdminExecution ad = new AdminExecution();
		if (adminList != null && adminList.size() > 0) {
			ad.setState(AdminStateEnum.GET_SECCESS.getState());
			ad.setAdminlist(adminList);
			ad.setCount(count);
		} else {
			ad.setState(AdminStateEnum.GET_FALSE.getState());
		}
		return ad;
	}
	
}
