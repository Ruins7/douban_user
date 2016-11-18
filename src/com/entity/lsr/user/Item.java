/**
 * 
 */
package com.entity.lsr.user;

import java.io.Serializable;

/**
 * @ClassName:     Item.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 推荐项目的通用模板
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月20日 上午9:58:54 
 */
public class Item implements Serializable {
	
	
	private String type_table;//哪张表
	private int id;//主键
	private String name;//名称
	private String imgs;//图片
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType_table() {
		return type_table;
	}
	public void setType_table(String type_table) {
		this.type_table = type_table;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	

}
