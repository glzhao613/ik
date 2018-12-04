package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Module;

public interface ModuleDao {
	
	/*增加新的模块*/
	int insertmodule(Module module);
	
	/*查询模块*/
	List<Module> queryModuleList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryModuleCount();
	
	Module querymodule(int moduleid);
	
	/*更新模块*/
	int upadtemodule(Module module);
	
	/*删除模块*/
	boolean deletemodule(int moduleid);
	boolean deleteadminmodule(int moduleid);
}
