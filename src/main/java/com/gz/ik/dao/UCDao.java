package com.gz.ik.dao;

import com.gz.ik.entity.UC;

public interface UCDao {
	/**
	 * 添加买课记录
	 * @param uC
	 * @return
	 */
	int insertUC(UC uC);
	
	/**
	 *删除买课记录
	 * @param uc
	 * @return
	 */
	int delUC(UC uc);

}
