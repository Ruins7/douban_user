package com.dao.hyy.city.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.hyy.city.CityDao;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.city.City;
/**
 * 城市dao层实现类
 * @author 胡伊杨
 *
 */
public class CityDaoImpl implements CityDao{

	 
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	
	@Override
	/**
	 * 查找城市
	 * @param id 城市的id
	 * @return 城市
	 */
	public City findCity(int id) {
		Connection conn = pool.getConnection();
		String sql ="select * from city where city_id=?";
		try {
			City city = qr.query(conn, sql, new BeanHandler(City.class), id);
			city.setCity_id(id);
			System.out.println("==========城市的名字："+city.getCity_desc());
			pool.releaseConnection(conn);
			return city;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
