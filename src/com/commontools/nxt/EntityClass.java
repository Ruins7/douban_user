/**
 * 
 */
package com.commontools.nxt;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:     entityClass.java
 * @Description:   从页面接收bean的值，然后封装bean(用于简单的表单封装，不含二进制) 
 *     
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014-9-10 下午05:52:39 
 */
public class EntityClass {
	
	public static <T> T returnEntity(T t, HttpServletRequest request){
         T obj = null;
         try {
        	 //获得对应类的实例
        	 Class cla = t.getClass();
        	 obj = (T) cla.newInstance(); 
        	 //获得对应实例的所有属性
        	 Field[] fields = cla.getDeclaredFields();
        	 //获得页面的所有数据
        	 Map<String,String[]> paramap = request.getParameterMap();	 
    		 //Map<String , Object> entitymap = new HashMap<String , Object>();
    		 
    		 for (Field field : fields) {
				field.setAccessible(true);
				String[] values = null;
				values = paramap.get(field.getName());
				//判断类型并且赋值
				//id是数据库自动赋值，程序无需管理，所以当遍历到id时没有页面的数据赋值，会发生异常！
				if(values != null){
					converter(obj, field, values);
				}	
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("参数实例化失败！");
			e.printStackTrace();
		}
		 return obj;
   }
	
	private static <T> void converter(T obj, Field field, String[] values) throws Exception{
		//获得当前循环的bean属性的类型
		Class type = (Class) field.getGenericType();
		if(String[].class == type){
			field.set(obj, values);	
		}else{
			String value = values[0];
			if(int.class == type){
				field.setInt(obj, Integer.parseInt(value));
			}else if(float.class == type){
				field.setFloat(obj, Float.parseFloat(value));
			}else if(double.class == type){
				field.setDouble(obj, Double.parseDouble(value));
			}else if(byte.class == type){
				field.setByte(obj, Byte.parseByte(value));
			}else if(short.class == type){
				field.setShort(obj, Short.parseShort(value));
			}else if(long.class == type){
				field.setLong(obj, Long.parseLong(value));	
			}else if(boolean.class == type){
				field.setBoolean(obj, Boolean.parseBoolean(value));
			}else if(char.class == type){
				field.setChar(obj, value.charAt(0));
			}else if(String.class == type){
				field.set(obj, value);
			}else if(Date.class == type){
				//需要不同格式的时间，在这里修改
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				field.set(obj, sdf.parse(value));	
			}
		} 
	}
}
	
	
	
