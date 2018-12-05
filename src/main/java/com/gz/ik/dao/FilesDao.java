package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.gz.ik.entity.Files;

public interface FilesDao {
	
	List<Files> queryFiles(Files files);
	
	int delFiles(Files files);
	
	List<Files> queryFilesList(@Param("files") Files files,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int insertFiles(Files files);
	
	int updateFiles(Files files);
	

}
