package com.dao.wj.commondao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 汪进
 *电影、图书、音乐排行榜，口碑
 * @param <T>
 * @param <PK>
 */
public interface GenericCommonDao<T , PK extends Serializable> {
	public List<Object[]> getRankingByCountEntity(T entity);
	
	public List<Object[]> getRankingByScoreEntity(T entity);

}
