package com.dao.wj.basedao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;

import com.dao.wj.dbuitil.DaoSupport;
/**
 * 基本dao层接口的实现类
 * @author 汪进
 *
 * @param <T>
 * @param <PK>
 */
public class GenericDaoImpl<T , PK extends Serializable> extends DaoSupport implements GenericDao<T, Serializable> {

	public Class<T> clazz;  
	private T entity;
    QueryRunner qr=new QueryRunner();
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() { 
		clazz = (Class<T>)( (ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			entity = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 根据实体id查询，并返回一个实体
     */
	@Override
	public T findByConditionId(Serializable id,String condition) {
		// TODO Auto-generated method stub
		String sql="select*from "+clazz.getSimpleName()+" where "+condition+"=?";
		Object[] params={id};
		try {
			return  (T) qr.query(getConn(), sql,new BeanHandler<T>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据实体id查询，并返回一个实体集合
	 */
	@Override
	public List<T> findByCondition(Serializable id,String condition) {
		// TODO Auto-generated method stub
		String sql="select*from "+clazz.getSimpleName()+" where "+condition+"=?";
		Object[] params={id};
		try {
			return   qr.query(getConn(), sql,new BeanListHandler<T>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		return null;
	}
   /**
    * 查询所以数据
    */
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from "+clazz.getSimpleName();
		try {
			return qr.query(getConn(), sql, new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    /**
     * 添加数据
     */
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		String[] filedName = ReflectUtil.getFiledName(entity);
		Object[] filedValue= ReflectUtil.getFiledValues(entity);
		String filedSQL = arrayToString(filedName, ",");
		String valueSQL = getQuestionMark(filedName);
		if(filedValue[0]!=null){
			String sql = "insert into "+clazz.getSimpleName()+" ("+filedSQL+")"+" values "+"("+valueSQL+")";
			System.out.println(sql);
			try {
				int affectRows = qr.update(getConn() , sql, filedValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 更新数据
	 */
	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		String[] filedName = ReflectUtil.getFiledName(entity);
		Object[] filedValue= ReflectUtil.getFiledValues(entity);
		String filedSQL = arrayToString(filedName, "=? ,");
		filedSQL = filedSQL.substring(0, filedSQL.length()-3)+"=?";
		System.out.println(filedSQL+" where "+filedName[0]+"="+filedValue[0]);
		String strSQL = "update "+clazz.getSimpleName()+" set "+filedSQL+" where "+filedName[0]+"="+filedValue[0];
		System.out.println(strSQL);
		try {
			int affectRows = qr.update(getConn(), strSQL , filedValue);
			System.out.println(affectRows + " 条记录被修改");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    /**
     * 根据实体id删除数据
     */
	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		String sql="delete from "+clazz.getSimpleName()+" where "+condition+"=?";
		Object[] params={id};
		try {
			qr.update(getConn(), sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据Ids集合，批量查找数据
	 */
	@Override
	public List<T> findByIds(List<Integer> Ids) {
		// TODO Auto-generated method stub
		List<T> list=new ArrayList<T>();
		String sql="select*from "+clazz.getSimpleName()+" where "+clazz.getSimpleName()+"_id=?";
		
		for (Iterator ids = Ids.iterator(); ids.hasNext();) {
			Integer id = (Integer) ids.next();
			try {
				list.add(qr.query(getConn(), sql, new BeanHandler<T>(clazz), id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 *  根据Ids集合，批量删除数据
	 */
	@Override
	public void delByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		String sql="delete from "+clazz.getSimpleName()+" where "+clazz.getSimpleName()+"_id=?";
		for (Iterator iterator = ids.iterator(); iterator.hasNext();) {
			Integer id = (Integer) iterator.next();
			try {
				qr.update(sql, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	
	
	// [a,b,c]   => "a,b,c"
	public static String arrayToString(String[] param , String link) {
		
		StringBuffer builder = new StringBuffer();
		
		for (int i = 0; i < param.length; i++) {
			builder.append(param[i]).append(link);
		}
		
		builder.deleteCharAt(builder.lastIndexOf(link));
		
		return builder.toString();
		
	}
	
	//生成问号  ?,?,?,?
	public static String getQuestionMark(String[] history) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < history.length; i++) {
			builder.append("?").append(",");
		}
		builder.deleteCharAt(builder.lastIndexOf(","));
		return builder.toString();
	}


    /**
     * 从数据库中拿出数据，并以condition为key生成map集合
     */
	@SuppressWarnings("unchecked")
	@Override
	public Map getMapEntity(T entity, String condition) {
		// TODO Auto-generated method stub
		String sql="select*from "+clazz.getSimpleName();
		try {
			return  qr.query(getConn(), sql, new KeyedHandler(condition));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<T> findSessionCondtionAll(LinkedList<Object> object) {
        LinkedList<T> clazzs=(LinkedList<T>) object;
        for (Iterator iterator = clazzs.iterator(); iterator.hasNext();) {
			T t = (T) iterator.next();
			
		}
		return null;
	}




}
