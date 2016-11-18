package com.commontools.lsr;


 
 import java.text.DateFormat;
 
 import java.text.SimpleDateFormat;
 
 import java.util.Date;
 
 import net.sf.json.JsonConfig;
 
 import net.sf.json.processors.JsonValueProcessor;
 
/**
434 
435 * @author robert.feng
436 
437 *
438 
 */
 
 public class DateJsonValueProcessor implements JsonValueProcessor {
 
 public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
 
 private DateFormat dateFormat;
 
 /**
48 
449 * 构造方法.
450 
451 *
452 
453 * @param datePattern 日期格式
454 
455 */
 
public DateJsonValueProcessor(String datePattern) {
 
 if(datePattern==null)
 
 dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
 
 else
 
 dateFormat = new SimpleDateFormat(datePattern);
 
 }
 
 /* （非 Javadoc）
470 
471 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object， net.sf.json.JsonConfig)
472 
473 */
 
 public Object processArrayValue(Object arg0,JsonConfig arg1) {
 
 // TODO 自动生成方法存根
 
 return process(arg0);
 
 }
 
 /* （非 Javadoc）
484 
485 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String， java.lang.Object， net.sf.json.JsonConfig)
486 
487 */
 
 public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
 
 // TODO 自动生成方法存根
 
 return process(arg1);
 
 }
 
 private Object process(Object value) {
 
 return dateFormat.format((Date) value);
 }
 }
