package com.dao.hyy.entity;


/**
 * 获得一个实体类
 * @author 胡伊杨
 *
 * @param <T>
 */
public interface EntityDao<T> {
	/**
	 * 获得一个实体类
	 * @param inTable_id
	 * @return
	 */
	public Object[] getEntity(int inTable_id);
	
	/**
	 * 查找实体类
	 * @param table_name
	 * @param inTable_id
	 * @return
	 */
	public Object[] findEntity(String table_name,int inTable_id);
	
	
}
