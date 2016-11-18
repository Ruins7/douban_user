package com.dao.wj.utildao;

import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


public class JsonUtil {



/** 
 * 将java对象转换成json字符串
 
 * @param javaObj
 
 * @return
 
 */
 
 public static String getJsonString4JavaPOJO(Object javaObj){
 
 JSONObject json;
 
 json = JSONObject.fromObject(javaObj);
 
 return json.toString();
 
 }
 
 /**
318 
319 * 将java对象转换成json字符串,并设定日期格式
320 
321 * @param javaObj
322 
323 * @param dataFormat
324 
325 * @return
326 
 */
 
public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
 
 JSONArray json;
 
 JsonConfig jsonConfig = configJson(dataFormat);
 
 //json = JSONArray.fromObject(javaObj,jsonConfig);
 json = (JSONArray) JSONSerializer.toJSON(javaObj, jsonConfig);
 return json.toString();
 
 }
 
 /**
 
 * @param args
 
 */
 
 public static void main(String args) {
 
 // TODO 自动生成方法存根
 
 }
 
 /**
 
 * JSON 时间解析器具
 
 * @param datePattern
 
 * @return
 
 */
 
public static JsonConfig configJson(String datePattern) {
 
 JsonConfig jsonConfig = new JsonConfig();
jsonConfig.setExcludes(new String[]{""});
 
 jsonConfig.setIgnoreDefaultExcludes(false);
 
 jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
 
 jsonConfig.registerJsonValueProcessor(Date.class,
 
 new DateJsonValueProcessor(datePattern));
 
 return jsonConfig;
 
 }
 
/**
 
 *
 
 * @param excludes
 
 * @param datePattern
 
 * @return
 
 */
 
 public static JsonConfig configJson(String excludes,
 
 String datePattern) {
 
 JsonConfig jsonConfig = new JsonConfig();
 
 jsonConfig.setExcludes(new String[]{excludes});
 
 jsonConfig.setIgnoreDefaultExcludes(false);
 
 jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
 
 jsonConfig.registerJsonValueProcessor(Date.class,
 
 new DateJsonValueProcessor(datePattern));
 
 return jsonConfig;
 
 }

 }
 
 /**
 
 * linkwise
 
 */
 
 