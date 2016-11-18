package com.commontools.lsr;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Hibernate;

/**
 * @ClassName:     entitybinClass.java
 * @Description:   
 *                 1.用于封装文件表单对象(上传文件到数据库,数据库存文件二进制的形式)
 *                   返回包含二进制的实体 
 * 
 * @author         Ruins7
 * @version        V2.0  
 * @Date           2014-9-10 下午05:52:39 
 */

public class EntityBinClass {
	
	private static final long MAX_SIZE = 16 * 1024 * 1024;// 16MB

	private static final String[] ALLOW_TYPE = new String[] { "image/bmp",
			"image/x-png", "image/png", "image/gif", "image/jpeg", "image/jpg",
			"image/pjpeg" };
	
	public static <T> T returnEntity(T t, HttpServletRequest request){
		
		        // 文本参数实例化+二进制参数实例化
		
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置文件的上限大小，超过大小的文件会以临时文件形式保存在磁盘，小于的文件会保存在内存
				factory.setSizeThreshold(16 * 1024 * 1024);// 16MB
				// 超过上限大小的文件，系统存放其临时文件的位置
				factory.setRepository(new File("E:/"));

				ServletFileUpload handler = new ServletFileUpload(factory);
				handler.setSizeMax(MAX_SIZE);// 设置文件大小为16MB
				handler.setHeaderEncoding("utf-8");
				
				T obj = null;
				File filetoserver = null;
				try {
					//获得对应类的实例
		        	Class cla = t.getClass();
		        	obj = (T) cla.newInstance();
		        	//获得对应实例的所有属性
		        	Field[] fields = cla.getDeclaredFields();
					//得到request中的所有参数
					List<FileItem> file = handler.parseRequest(request);
					//遍历页面得到的每一个参数类型判断类型并且赋值
					Iterator<FileItem> it = file.iterator();
					List mulstring = new ArrayList();
					Field fieldss = null;
					while (it.hasNext()) {
						FileItem item = it.next();
						// 文本参数
						if (item.isFormField()) {	
							for (Field field : fields) {
								field.setAccessible(true);
								String[] value = null;
								if(field.getName().equalsIgnoreCase(item.getFieldName())){							 
									Class clazz = (Class)field.getGenericType();
									//判断当前属性的类型， 如果是String[],在这可以判断其他类型的多参数
									if(String[].class == clazz){
										mulstring.add(item.getString("utf-8"));
										fieldss = field;
									}else{
										String[] v = {item.getString("utf-8")};
										value = v;
										//判断类型并且赋值
										//id是数据库自动赋值，程序无需管理，所以当遍历到id时没有页面的数据赋值，会发生异常！
										if(value != null){
											converter(obj, field, value);
										}	
									}				
								}
							}					 
						} else {
							// 文件参数
							// 判断文件大小
							if (item.getSize() <= MAX_SIZE) {
								// 判断文件类型
								if (ArrayUtils.contains(ALLOW_TYPE,
										item.getContentType())) {
									// 获取文件名称
									String filename = item.getName();
									filename = UUID.randomUUID() + filename;
									//真实上传地址(用户本地地址,临时文件）
									File dir = new File("E://uploadtem");
									if(!dir.exists()){
										dir.mkdir();
									}
									filetoserver = new File(dir,
											filename);
									item.write(filetoserver);
									//将filetoserver封装进bean中
									for (Field field : fields) {
										field.setAccessible(true);
										if(field.getName().equalsIgnoreCase(item.getFieldName())){
											converterBin(obj, field, filetoserver);
										}	
									}						    
								}else{
									System.out.println("文件类型有误！");
								}
							}else{
								System.out.println("文件太大！");
							}
						}
					} 
					//等待所有参数分析完毕之后分析 mulstring 中的参数
					//判断多参数类型的mulstring中是否有值
					if(mulstring.size() > 0){
						//若有值则转为String[]
						String[] values = (String[]) mulstring.toArray(new String[mulstring.size()]);						
						converter(obj, fieldss, values);
						mulstring = null;
					}    
				} catch (Exception e) {
					e.printStackTrace();
				} 
		return obj;	
	}
	
	//普通类型的文本参数封装
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
	
	    //二进制类型的参数封装
		private static <T> void converterBin(T obj,Field field, File file) throws Exception{
			//获得当前循环的bean属性的类型
			FileInputStream in = new FileInputStream(file);
			Blob blob = Hibernate.createBlob(in);
			field.set(obj, blob);
		}

}
