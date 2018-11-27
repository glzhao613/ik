/**
 * 
 */
package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import com.gz.ik.entity.News;

/**
 * @author lvli2
 *
 */
public interface NewsDao {
	//查询所有资讯并返回一个List列表
	List<News> queryAllNews();
	//根据指定参数查询指定条数的资讯
	List<News> queryNews(Map<String,Object> pageMap);
	//根据资讯标题查询
	News queryOneNews(String newstitle);
	//插入一条资讯
	int insertNews(News news);
	//修改一条资讯
	int updateNews(News news);
	//删除一条资讯
	boolean deleteNews(News news);
}
