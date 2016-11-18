package com.commontools.lsr;

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
//将对象转为json
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
//传输对象中如果有DATE类型，且需要传输Array
public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
 JSONArray jsons;
 JsonConfig jsonConfig = configJson(dataFormat);
 jsons = (JSONArray) JSONSerializer.toJSON(javaObj, jsonConfig);
 return jsons.toString();
 
 }

//传输对象中如果有DATE类型，且需要传输Object
public static String getJsonString4JavaPOJOObj(Object javaObj , String dataFormat){
	JSONObject jsons;
	JsonConfig jsonConfig = configJson(dataFormat);	
	jsons = (JSONObject) JSONSerializer.toJSON(javaObj, jsonConfig);
    return jsons.toString();	
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

 