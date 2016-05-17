package com.xuping.sas.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xuping.sas.annotation.FieldMeta;
import com.xuping.sas.annotation.TableMeta;

/** 
 * @author jing 
 *  反射工具 
 */  
public class ReflectHelper {  
	/** 
	 * 获取obj对象fieldName的Field 
	 * @param obj 
	 * @param fieldName 
	 * @return 
	 */  
	public static Field getFieldByFieldName(Object obj, String fieldName) {  
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass  
				.getSuperclass()) {  
			try {  
				return superClass.getDeclaredField(fieldName);  
			} catch (NoSuchFieldException e) {  
			}  
		}  
		return null;  
	}  
	
	/**
	 * 获取对象的属性
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String,Object> getFieldsByObject(Object obj) throws IllegalArgumentException, IllegalAccessException{
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass  
				.getSuperclass()) {  
			Map<String,Object> map = new HashMap<String, Object>();
			Field[] fs = superClass.getDeclaredFields();  
			for (int i = 0; i < fs.length; i++) {
				Field field = fs[i];
				if(field!=null){
					if (field.isAccessible()) {  
						map.put(field.getName(), field.get(obj));
					} else {  
						field.setAccessible(true);  
						map.put(field.getName(), field.get(obj)); 
						field.setAccessible(false);  
					}  
				}
			}
			return map;
		}  
		return null;
	}
	
	/**
	 *移除map中的null 
	 * @param map
	 * @return
	 */
	public static Map<String,String> removeMapNull(Map<String,Object> map){
		Iterator<String> it = map.keySet().iterator();
		Map<String,String> map2 = new HashMap<String, String>();
		while(it.hasNext()){
			String n = it.next();
			Object object=map.get(n);
			if(object!=null){
				map2.put(n, map.get(n).toString());
			}
		}
		return map2;
	}
	/** 
	 * 获取obj对象fieldName的属性值 
	 * @param obj 
	 * @param fieldName 
	 * @return 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */  
	public static Object getValueByFieldName(Object obj, String fieldName)  
			throws SecurityException, NoSuchFieldException,  
			IllegalArgumentException, IllegalAccessException {  
		Field field = getFieldByFieldName(obj, fieldName);  
		Object value = null;  
		if(field!=null){  
			if (field.isAccessible()) {  
				value = field.get(obj);  
			} else {  
				field.setAccessible(true);  
				value = field.get(obj);  
				field.setAccessible(false);  
			}  
		}  
		return value;  
	}  

	/** 
	 * 设置obj对象fieldName的属性值 
	 * @param obj 
	 * @param fieldName 
	 * @param value 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */  
	public static void setValueByFieldName(Object obj, String fieldName,  
			Object value) throws SecurityException, NoSuchFieldException,  
			IllegalArgumentException, IllegalAccessException {  
		Field field = obj.getClass().getDeclaredField(fieldName); 
		if (field.getGenericType().toString().equals(
			      "class java.lang.Integer")){
			value =Integer.parseInt(value.toString());  	
		}else if(field.getGenericType().toString().equals(
			      "class java.util.Date")){
			value = DateUtil.gettime(value.toString());
		}
		if (field.isAccessible()) {  
			field.set(obj, value);  
		} else {  
			field.setAccessible(true);  
			field.set(obj, value);  
			field.setAccessible(false);  
		}  
	}  
/*	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Map<String,Object> m=new HashMap<String,Object>();
		ceshi(m);
		System.out.println(m);		
	}*/
	public static void ceshi(Object obj){
		Map<String,Object> m=(HashMap<String,Object>) obj;
		m.put("1", "asda");
		System.out.println(m==obj);
	}
	/**
	 * 根据模型获取注解
	 * @param entity
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static List<SortableField> init(String className) throws ClassNotFoundException{  
		List<SortableField> list = new ArrayList<SortableField>();  
		//FieldMeta filed = entity.getAnnotation(FieldMeta.class);  
		Class<?> entity = Class.forName(className);
		if(entity!=null){  

			/**返回类中所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段 
			 * entity.getFields();只返回对象所表示的类或接口的所有可访问公共字段 
			 * 在class中getDeclared**()方法返回的都是所有访问权限的字段、方法等； 
			 * 可看API 
			 * */  
			Field[] fields = entity.getDeclaredFields();  
			//            
			for(Field f : fields){  
				//获取字段中包含fieldMeta的注解  
				FieldMeta meta = f.getAnnotation(FieldMeta.class);  
				if(meta!=null){  
					SortableField sf = new SortableField(meta, f);  
					if(sf.getMeta().summary()&&sf.getMeta().isimport())
						list.add(sf);  
				}  
			}  

			//返回对象所表示的类或接口的所有可访问公共方法  
			Method[] methods = entity.getMethods();  

			for(Method m:methods){  
				FieldMeta meta = m.getAnnotation(FieldMeta.class);  
				if(meta!=null){  
					SortableField sf = new SortableField(meta,m.getName(),m.getReturnType());  
					if(sf.getMeta().summary()&&sf.getMeta().isimport())
						list.add(sf);  
				}  
			}  
			//这种方法是新建FieldSortCom类实现Comparator接口，来重写compare方法实现排序  
			//          Collections.sort(list, new FieldSortCom());  
			Collections.sort(list, new Comparator<SortableField>() {  
				@Override  
				public int compare(SortableField s1,SortableField s2) {  
					return s1.getMeta().order()-s2.getMeta().order();  
					//                  return s1.getName().compareTo(s2.getName());//也可以用compare来比较  
				}  

			});  
		}  
		return list;  

	}
	public static String getTableName(String className){
		try{
			Class clazz = Class.forName(className);
			if(clazz.isAnnotationPresent(TableMeta.class)){
				TableMeta tm = (TableMeta)clazz.getAnnotation(TableMeta.class);
				return tm.value();
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	/**
	 * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
	 * 例如：HelloWorld->HELLO_WORLD
	 * @param name 转换前的驼峰式命名的字符串
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}
	/**
	 * 根据类型获取所有注解字段
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<String> getPropertys(String className) throws ClassNotFoundException{
		List<String> s = new ArrayList<String>();
		List<SortableField> ls = init(className);
		for (int i = 0; i < ls.size(); i++) {
			s.add(ls.get(i).getName());
		}
		return s;
	}
	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel :  camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/** 
	 * 将javaBean转换成Map 
	 * 
	 * @param javaBean javaBean 
	 * @return Map对象 
	 */ 
	public static Map<String, String> toMap(Object javaBean) 
	{ 
		Map<String, String> result = new HashMap<String, String>(); 
		Method[] methods = javaBean.getClass().getDeclaredMethods(); 

		for (Method method : methods) 
		{ 
			try 
			{ 
				if (method.getName().startsWith("get")) 
				{ 
					String field = method.getName(); 
					field = field.substring(field.indexOf("get") + 3); 
					field = field.toLowerCase().charAt(0) + field.substring(1); 

					Object value = method.invoke(javaBean, (Object[])null);
					result.put(field, null == value ? "" : value.toString()); 
				} 
			} 
			catch (Exception e) 
			{ 
			} 
		} 

		return result; 
	} 
	public static Map<String, Object> toMapObj(Object javaBean) 
	{ 
		Map<String, Object> result = new HashMap<String, Object>(); 
		Method[] methods = javaBean.getClass().getDeclaredMethods(); 

		for (Method method : methods) 
		{ 
			try 
			{ 
				if (method.getName().startsWith("get")) 
				{ 
					String field = method.getName(); 
					field = field.substring(field.indexOf("get") + 3); 
					field = field.toLowerCase().charAt(0) + field.substring(1); 
					Object value = method.invoke(javaBean, (Object[])null);
					if(null == value ){
						continue;
					}
					result.put(field,value.toString()); 
				}
			} 
			catch (Exception e) 
			{ 
			} 
		} 

		return result; 
	} 
	/** 
	 * 将javaBean转换成Map 去除为Null字段
	 * 
	 * @param javaBean javaBean 
	 * @return Map对象 
	 */ 
	public static Map<String, String> toMapRemoveNull(Object javaBean) 
	{ 
		Map<String, String> result = new HashMap<String, String>(); 
		Method[] methods = javaBean.getClass().getDeclaredMethods(); 

		for (Method method : methods) 
		{ 
			try 
			{ 
				if (method.getName().startsWith("get")) 
				{ 
					String field = method.getName(); 
					field = field.substring(field.indexOf("get") + 3); 
					field = field.toLowerCase().charAt(0) + field.substring(1); 

					Object value = method.invoke(javaBean, (Object[])null);
					if(null == value ){
						continue;
					}
					result.put(field,value.toString()); 
				}
			} 
			catch (Exception e) 
			{ 
			} 
		} 

		return result; 
	} 
	/** 
     * 将json对象转换成Map 
     * 
     * @param jsonObject json对象 
     * @return Map对象 
     */ 
    @SuppressWarnings("unchecked") 
    public static Map<String, String> toMap(JSONObject jsonObject) 
    { 
        Map<String, String> result = new HashMap<String, String>(); 
        Iterator<String> iterator = jsonObject.keySet().iterator(); 
        String key = null; 
        String value = null; 
        while (iterator.hasNext()) 
        { 
            key = iterator.next(); 
            value = jsonObject.getString(key); 
            result.put(key, value); 
        } 
        return result; 
    } 
    /** 
     * 将map转换成Javabean 
     * 
     * @param javabean javaBean 
     * @param data map数据 
     */ 
    public static Object toJavaBean(Object javabean, Map<String, String> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("set")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    method.invoke(javabean, new Object[] 
                    { 
                        data.get(field) 
                    }); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return javabean; 
    } 
    
	public static void main(String[] args) {
		try {

			List<SortableField> list = init("com.mydao.safe.model."+"Abarbeitung");//获取泛型中类里面的注解  
			//输出结果  
			int i = 1;
			for(SortableField l : list){  
				System.out.println("字段名称："+l.getName()+"\t字段类型："+l.getType()+  
						"\t注解名称："+l.getMeta().name()+"\t注解描述："+l.getMeta().description());  
				System.out.println(i++);
			}
			System.out.println(getTableName("com.mydao.safe.model."+"Abarbeitung"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
     * 根据属性名 得到该属性的getter方法名 
     */  
    public static String getGetterNameByFiledName(String fieldName){  
        return "get" + fieldName.substring(0 ,1).toUpperCase() + fieldName.substring(1) ;  
    } 
    /** 
     * 根据目标方法和注解类型  得到该目标方法的指定注解 
     */  
    public static Annotation getAnnotationByMethod(Method method , Class annoClass){  
        Annotation all[] = method.getAnnotations();  
        for (Annotation annotation : all) {  
            if (annotation.annotationType() == annoClass) {  
                return annotation;  
            }  
        }  
        return null;  
    } 
    /** 
     * 根据类和方法名得到方法 
     */  
    public static Method getMethodByClassAndName(Class c , String methodName){  
        Method[] methods = c.getDeclaredMethods();  
        for (Method method : methods) {  
            if(method.getName().equals(methodName)){  
                return method ;  
            }  
        }  
        return null;  
    }
} 
