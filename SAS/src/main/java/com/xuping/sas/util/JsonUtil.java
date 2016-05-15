package com.xuping.sas.util;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
@SuppressWarnings({"rawtypes","unused"})
public class JsonUtil {
	/**
	 * mapתjson
	 * @param map
	 * @return
	 */
	public static JsonObject maptojson(Map map){
		JsonObject j = new JsonObject();
		j.addProperty(new Gson().toJson(map), true);
		return j;
	}
	/**
	 * stringתjson
	 * @param str
	 * @return
	 */
	public static JsonObject stringTojson(String str){
		JsonObject j = new JsonObject();
		j.addProperty(str, true);
		return j;
	}
	/**
	 * 此方法无用
	 * @param str
	 * @return
	 */
	public static Map<String,Object> stringToMap(String str){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		Map<String,Object> map = g.fromJson(str,new TypeToken<Map<String,Object>>(){}.getType());
		return map;
	}

	public static Map<String,Object> jsonToMap(JsonObject jb){
		GsonBuilder gb = new GsonBuilder();
		Gson g = new Gson();
		Map<String,Object> map = g.fromJson(jb,new TypeToken<Map<String,Object>>(){}.getType());
		return map;
	}
	/**
	 * listתjson
	 * @param list
	 * @return
	 */
	public static <T> String listtojson(List<T> list){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		return g.toJson(list);
	}
	public static String objecttojson(Object o){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		return g.toJson(o);
	}
	/**
	 * jsonתList<T>
	 * @param str
	 * @return
	 */
	public static <T> List<T> convert(String str){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		List<T> map = g.fromJson(str,new TypeToken<List<T>>(){}.getType());
		return map;
	}
	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(JsonObject json){
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
			Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof JsonArray)
				map.put((String) key, toList((JsonArray) value));
			else if(value instanceof JsonObject)
				map.put((String) key, toMap((JsonObject) value));
			else
				map.put((String) key, value);
		}
		return map;
	}
	/**
	 * 将JSONArray对象转换成List集合
	 * @param json
	 * @return
	 */
	public static List<Object> toList(JsonArray json){
		List<Object> list = new ArrayList<Object>();
		for (int i=0; i<json.size(); i++){
			Object value = json.get(i);
			if(value instanceof JsonArray){
				list.add(toList((JsonArray) value));
			}
			else if(value instanceof JsonObject){
				list.add(toMap((JsonObject) value));
			}
			else{
				list.add(value);
			}
		}
		return list;
	}


	/**
	 * 获取JsonObject
	 * @param json
	 * @return
	 */
	public static JsonObject parseJson(String json){
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}
	/**
	 * 根据json字符串返回Map对象
	 * @param json
	 * @return
	 */
	public static Map<String,Object> toMap(String json){
		return JsonUtil.toMap(JsonUtil.parseJson(json));
	}
}

