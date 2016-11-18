package com.dao.hyy.city;

import com.entity.hyy.city.City;
/**
 * 城市dao层
 * @author 胡伊杨
 *
 */
public interface CityDao {
	/**
	 * 查找城市
	 * @param id 城市的id
	 * @return 城市
	 */
	public City findCity(int id);
}
