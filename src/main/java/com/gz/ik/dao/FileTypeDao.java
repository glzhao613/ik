package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.FileType;

public interface FileTypeDao {
	
	List<FileType> queryFileType(FileType fileType);
	
	int delFileType(FileType fileType);
	
	List<FileType> queryFileTypeList(@Param("fileType") FileType fileType,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int insertFileType(FileType fileType);
	
	int updateFileType(FileType fileType);
	

}
