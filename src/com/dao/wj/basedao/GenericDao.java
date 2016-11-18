package com.dao.wj.basedao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author 汪进
 *基本dao层接口
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T , PK extends Serializable> {
	  /**
	   *                
	   * @param id
	   * @param condition(实体Id)
	   * @return entity
	   */
	public T findByConditionId( PK id,String condition ); 
	/**
	 * 
	 * @param id
	 * @param condition(实体Id)
	 * @return List<T>
	 */
	public List<T> findByCondition( PK id,String condition );
	/**
	 * 查询表中所有数据
	 * @return List<T>
	 */
	public List<T>findAll();
	/**
	 * 添加数据
	 * @param entity
	 */
	public void save(T entity);
	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 以condition为key生成map
	 * @param entity
	 * @param condition
	 * @return map
	 */
	public Map getMapEntity(T entity,String condition);
	/**
	 * 删除数据
	 * @param id
	 * @param condition
	 */
	public void deleteByConditionId( PK id,String condition );
	/**
	 * 获取session(未用)
	 * @param object
	 * @return
	 */
	public List<T>findSessionCondtionAll(LinkedList<Object> object);
    /**
     * 
     * @param ids
     * @return List<T>
     */
	public List<T> findByIds( List<Integer> ids);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delByIds( List<Integer> ids);

}
