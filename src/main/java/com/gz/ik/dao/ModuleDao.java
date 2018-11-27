package com.gz.ik.dao;

import com.gz.ik.entity.Module;

public interface ModuleDao {
	
	/*增加新的模块*/
	int insertmodule(Module module);
	
	/*查询模块*/
	Module querymodule(String modulename);
	
	/*更新模块*/
	int upadtemodule(Module module);
	
	/*删除模块*/
	boolean deletemodule(int moduleid);
	boolean deleteadminmodule(int moduleid);
}
