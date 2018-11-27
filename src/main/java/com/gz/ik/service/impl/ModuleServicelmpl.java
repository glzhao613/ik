package com.gz.ik.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.ik.dao.ModuleDao;
import com.gz.ik.dto.ModuleAddExecution;
import com.gz.ik.dto.ModuleDeleteExecution;
import com.gz.ik.dto.ModuleQuerExecution;
import com.gz.ik.dto.ModuleUpdateExecution;
import com.gz.ik.entity.Module;
import com.gz.ik.enums.ModuleAddStateEnum;
import com.gz.ik.enums.ModuleDeleteStateEnum;
import com.gz.ik.enums.ModuleQuerStateEnum;
import com.gz.ik.enums.ModuleUpdateStateEnum;
import com.gz.ik.service.ModuleService;

@Service
public class ModuleServicelmpl implements ModuleService {
	
	@Autowired
	ModuleDao moduleDao;
	
	
	@Override
	public ModuleAddExecution AddCheck(Module module) throws RuntimeException {
		Module i_module=null;
		if(module==null) {
			return new ModuleAddExecution(ModuleAddStateEnum.NULL_INPUT);
		}
		else {
			i_module=moduleDao.querymodule(module.getModuleName());
			if(i_module!=null) {
				return new ModuleAddExecution(ModuleAddStateEnum.ID_ERROR);
			}
			else {
				int count;
				count=moduleDao.insertmodule(module);
				if(count>0) {
					return new ModuleAddExecution(ModuleAddStateEnum.PASS,module);
				}
				else {
					return new ModuleAddExecution(ModuleAddStateEnum.ERROR);
				}
			}
		}
	}


	@Override
	public ModuleQuerExecution QuerCheck(Module module) throws RuntimeException {
		Module i_module=null;
		if(module==null) {
			return new ModuleQuerExecution(ModuleQuerStateEnum.NULL_INPUT);
		}
		else {
			i_module=moduleDao.querymodule(module.getModuleName());
			if(i_module!=null) {
				return new ModuleQuerExecution(ModuleQuerStateEnum.PASS,i_module);
			}
			else {
				return new ModuleQuerExecution(ModuleQuerStateEnum.ERROR);
			}
		}
	}


	@Override
	public ModuleUpdateExecution UpdateCheck(Module module) throws RuntimeException {
		if(module==null) {
			return new ModuleUpdateExecution(ModuleUpdateStateEnum.NULL_INPUT);
		}
		else {
			int count;
			count=moduleDao.upadtemodule(module);
			if(count>0) {
				return new ModuleUpdateExecution(ModuleUpdateStateEnum.PASS);
			}
			else {
				return new ModuleUpdateExecution(ModuleUpdateStateEnum.ERROR);
			}
		}
	}

	@Transactional
	@Override
	public ModuleDeleteExecution DeleteCheck(Module module) throws RuntimeException {
		if(module==null) {
			return new ModuleDeleteExecution(ModuleDeleteStateEnum.NULL_INPUT);
		}
		else {
			boolean da_module=false;
			boolean d_module=false;
			Module i_module=null;
			i_module=moduleDao.querymodule(module.getModuleName());
			da_module=moduleDao.deleteadminmodule(i_module.getModuleId());
			if(da_module) {
				d_module=moduleDao.deletemodule(i_module.getModuleId());
				if(d_module) {
					return new ModuleDeleteExecution(ModuleDeleteStateEnum.PASS);
				}
				else {
					return new ModuleDeleteExecution(ModuleDeleteStateEnum.ERROR);
				}
			}
			else {
				return new ModuleDeleteExecution(ModuleDeleteStateEnum.ERROR);
			}
		}
	}

}
