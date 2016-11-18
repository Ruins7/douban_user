package com.dao.wj.utildao;

import java.io.Serializable;
import java.util.List;


public interface GenericUtilDao<T , PK extends Serializable> {
	public List<Object[]> setpageEntity(T entity);

}
