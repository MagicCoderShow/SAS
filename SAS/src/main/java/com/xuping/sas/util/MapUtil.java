package com.xuping.sas.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;
@SuppressWarnings({"rawtypes"})
public class MapUtil{
	public static <T> T MapToObject(HashMap<String,Object> map,Class<T> class1) throws InstantiationException, IllegalAccessException, IllegalArgumentException, Exception {
		Field[] fields=class1.getDeclaredFields();
		T t = null;
		if(fields.length>0)
		{
			t=class1.newInstance();
		}
		boolean flag;
		for (Field field : fields) {
			if(map.containsKey(field.getName())&&map.get(field.getName())!=null)
			{
				flag=false;
				if(!field.isAccessible())
				{
					field.setAccessible(true);
					flag=true;
				}
				if((field.getType() == java.util.Date.class || field.getType() == java.sql.Date.class) && map.get(field.getName()).getClass()!=field.getType())
				{//时间类型的转换
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					field.set(t,format.parse((String)map.get(field.getName())));
				}else if(field.getType() == java.sql.Timestamp.class && map.get(field.getName()).getClass()!=field.getType())
				{//Timestamp转换
					field.set(t,Timestamp.valueOf((String)map.get(field.getName())));
				}else if(field.getType() == java.lang.Long.class &&map.get(field.getName()).getClass()!=field.getType())
				{//Long
					field.set(t,Long.valueOf((String)map.get(field.getName())));
				}else if((field.getType() == int.class || field.getType() == java.lang.Integer.class)&& map.get(field.getName()).getClass()!=field.getType())
				{//int
					field.set(t,Integer.parseInt((String)map.get(field.getName())));
				}else
				{
					field.set(t,map.get((String)field.getName()));
				}
				if(flag)
				{
					field.setAccessible(false);
				}
			}
		}
		return t;
	}
	//類轉Map<String,Object>
	public static Map<String, Object> beanToMap(Object entity){  
        Map<String, Object> parameter = new HashMap<String, Object>();  
         Field[]   fields   =   entity.getClass().getDeclaredFields();  
        for(int i = 0; i < fields.length; i++){  
            String fieldName =  fields[i].getName();  
            Object o = null;  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
               String getMethodName = "get" + firstLetter + fieldName.substring(1);  
               Method getMethod;  
            try {  
                getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});  
                 o = getMethod.invoke(entity, new Object[] {});  
            } catch (Exception e) {  
                e.printStackTrace();  
            }    
            if(o != null){  
                parameter.put(fieldName, o);  
            }  
        }  
        return parameter;  
    }
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, String> map) {  
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, String> entry : map.entrySet()) {  
	    	if(!StringUtils.isBlank(entry.getValue())){
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");
	    	}
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = StringUtils.substringBeforeLast(s, "&");  
	    }  
	    return s;  
	}
	
	public static String getKey(Map map,String value,boolean islike){
		 Set set=map.entrySet();
		 Iterator it = set.iterator();
		 while(it.hasNext()){
			 Map.Entry entry=(Map.Entry)it.next();
			 if(islike){
				 if(StringUtils.contains(entry.getValue().toString(),value)){
					return entry.getKey().toString(); 
				 }
			 }else{
				 if(entry.getValue().equals(value)) return entry.getKey().toString();
			 }
		 }
		 return "";
	}
	public static Integer getIntegerByDoubleStr(Object doublestr){
		Double b = Double.parseDouble(doublestr.toString());
		return Integer.valueOf(b.intValue());
	}
	
	public static String getPercent(Integer x,Integer total){ 
		   if(x==null||total==null||total==0||x==0){
			  return "0.00%"; 
		   }
		   String result="";//接受百分比的值  
		   double x_double=x*1.0;  
		   double tempresult=x_double/total;  
		   //NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法  
		   //nf.setMinimumFractionDigits( 2 );        保留到小数点后几位  
		   DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐  
		   //result=nf.format(tempresult);     
		   result= df1.format(tempresult);    
		   return result;  
	} 
	
	/**
	 * 从一个Map中取出需要的key,如果key不存在返回空字符串
	 * @param inMap		源map
	 * @param outMap	需要的map
	 * @param keys		数组key
	 * @return 根据key取出来的map
	 */
	public static Map<String,Object>  getNeedKey(Map<String,Object> inMap,String[] keys){
		Map<String,Object> outMap = new HashMap<String, Object>();
		for (String key : keys) {
			if(inMap.containsKey(key)){
				if(null!=inMap.get(key)){
					outMap.put(key,inMap.get(key));
				}else{
					outMap.put(key,"");
				}
			}else{
				outMap.put(key,"");
			}
		}
		return outMap;
	}
	
	public static void main(String[] args) {
		System.out.println(MapUtil.compareStrings("1,2,3","2,3,4").toString());
	}
	public static Integer getIntegerValue(Map<String,Object> params,String key){
		if(params.get(key)!=null&&!Strings.isNullOrEmpty(params.get(key).toString())){
			return Integer.parseInt(params.get(key).toString());
		}
		return null;
	}
	public static Long getLongValue(Map<String,Object> params,String key){
		if(params.get(key)!=null){
			return Long.parseLong(params.get(key).toString());
		}
		return null;
	}
	public static String getStringValue(Map<String,Object> params,String key){
		if(params.get(key)!=null){
			return params.get(key).toString();
		}
		return null;
	}
	public static Integer parseInt(String i){
		if(Strings.isNullOrEmpty(i)){
			return null;
		}else{
			return Integer.parseInt(i);
		}
	}
	/**
	 * 两个逗号分隔的字符串比较，返回old中缺少的部分和多余的部分
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static Map<String,List<String>> compareStrings(String oldStr,String newStr){
		Map<String,List<String>> map = new HashMap<String, List<String>>();
		List<String> add = null;
		List<String> sub = null;
		Set<String> oldSet = new HashSet<String>(Arrays.asList(oldStr.split(",")));
		Set<String> newSet = new HashSet<String>(Arrays.asList(newStr.split(",")));
		for (Iterator<String> iterator = newSet.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			if(!oldSet.contains(str)){
				add = new ArrayList<String>();
				//新增的
				add.add(str);
			}
			
		}
		for (Iterator<String> iterator = oldSet.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			if(!newSet.contains(str)){
				sub = new ArrayList<String>();
				//减少的
				sub.add(str);
			}
			
		}
		map.put("add", add);
		map.put("sub", sub);
		return map;
	}
}
