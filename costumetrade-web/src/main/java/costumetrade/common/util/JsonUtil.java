package costumetrade.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 *  <p>Title: JsonUtil.java<／p>
 *  <p>Description: <／p>
 *  @author yh.yu
 *  @date  2015年8月25日
 */
public class JsonUtil {
	private static final String JSON_DATE_FORAMT = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * 将某个类的属性变为Json（通过反射将含有get的方法）
	 * @param obj
	 * @param needlessMethod
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(Object obj, String[] needlessMethod){
		if(null == obj)
			return "{}";
		StringBuilder bu = new StringBuilder();
		try{
			bu.append("{");
			Method[] methods = obj.getClass().getMethods();
			for(Method method : methods){
				String methodName = method.getName();
				if(needless(methodName, needlessMethod))
					continue;
				Class<?>[] classes = method.getParameterTypes();
				if(classes!=null&&classes.length>0)
					continue;
				if(methodName.startsWith("get") && ! methodName.equals("getClass")){
					String name = methodName.replaceFirst("get", "");
					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
					bu.append("'").append(name).append("':");
					Object value = method.invoke(obj);
					if(value instanceof List){
						bu.append(toJson((List)value)).append(",");
					}else{
						value = value == null ? "" : value;
						bu.append("'").append(value.toString()).append("',");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(bu.toString().endsWith(",")) bu.deleteCharAt(bu.length() - 1);
		bu.append("}");
		return bu.toString();
	}
	/**
	 * 转换为Json
	 * @param obj
	 * @param includeMethod
	 * @param needlessMethod
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(Object obj,String [] includeMethod, String[] needlessMethod,boolean ignoreNeedLess){
		if(null == obj)
			return "{}";
		StringBuilder bu = new StringBuilder();
		try{
			bu.append("{");
			for(String methodName : includeMethod){
				if(!ignoreNeedLess&&needless(methodName, needlessMethod))
					continue;
				if(methodName.startsWith("get") && ! methodName.equals("getClass")){
					String name = methodName.replaceFirst("get", "");
					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
					bu.append("'").append(name).append("':");
					Object value = obj.getClass().getMethod(methodName).invoke(obj);
					if(value instanceof List){
						bu.append(toJson((List)value)).append(",");
					}else{
						value = value == null ? "" : value;
						bu.append("'").append(value.toString()).append("',");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(bu.toString().endsWith(",")) bu.deleteCharAt(bu.length() - 1);
		bu.append("}");
		return bu.toString();
	}
	
	public static String toJson(Object obj){
		return toJson(obj, null);
	}
	/**
	 * 将某个类的属性变为Json（通过反射将含有get的方法）
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(List list){
		return toJson(list, null);
	}
	
	/**
	 * 将某个类的属性变为Json（通过反射将含有get的方法）
	 * @param list
	 * @param needlessMethod
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(List list, String[] needlessMethod){
		StringBuilder bu = new StringBuilder();
		bu.append("[");
		for(Object obj : list){
			if(obj == null) continue;
			if(isPrimitive(obj))
				bu.append("'").append(obj).append("',");
			else 
				bu.append(toJson(obj, needlessMethod)).append(",");
		}
		if(bu.toString().endsWith(",")) bu.deleteCharAt(bu.length() - 1);
		bu.append("]");
		return bu.toString();
	}
	
	/**
	 * @param methodName
	 * @param needlessMethod
	 * @return
	 */
	private static boolean needless(String methodName, String[] needlessMethod){
		if(needlessMethod == null || needlessMethod.length == 0) 
			return false;
		
		int size = needlessMethod.length;
		for(int i = 0; i < size; i ++){
			if(methodName.equals(needlessMethod[i]))
				return true;
		}
		return false;
	}
	/**
	 * 判断是否是基本类型
	 *
	 * @return
	 * @throws
	 */
	private static boolean isPrimitive(Object object){
		if(object instanceof String)
			return true;
		else if(object instanceof Boolean)
			return true;
		else if(object instanceof Number)
			return true;
		else if(object instanceof Character)
			return true;
		else 
			return false;
	}
	
	/**
     * 将对象转换为传入类型的对象
     * @param 
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass){
        JSONObject jsonObject = JSONObject.fromObject(object);
//        return (T) JSONObject.toBean(jsonObject, beanClass);
        return convertToObj(jsonObject, beanClass);
    }
    
    /**
     * 将JSON对象数组转换为传入类型的List
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass){
    	List<T> list = convertToList(jsonArray, objectClass);
        return list;
    }
    
    /**
     * 过滤JSON null值
     * @return
     */
    public static JsonConfig toJSONNullFilter(){
        JsonConfig config=new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
//               if(JSONNull.getInstance()==value){
               if(value instanceof JSONNull){
                   return true;
               }
               return false;
            }
        });
        return config;
    }
    
    /**
     * 将JSONObject转换成Object对象（通过反射将含有set的方法）
     * @param jsonObject
     * @param cla
     * @return
     */
    public static <T> T convertToObj(JSONObject jsonObject,Class<T> cla){
//        System.err.println(jsonObject.isEmpty()+"   "+jsonObject.isNullObject());
        if(jsonObject==null||jsonObject.isEmpty()) return null;
        
//        Field[] fb  =cla.getDeclaredFields();
        Field[] fb = getDeclaredFields(cla);
        T t;
        try {
            t = cla.newInstance();
            String needlessMethod[] = {"serialVersionUID"};
            for(int i=0;i<fb.length;i++){
                Field f = fb[i];
                f.setAccessible(true);//设置属性是可以访问的
                String fieldName = f.getName();
                String clazz = f.getType().getName();//得到此属性的类型  
                if(needless(fieldName, needlessMethod))
                    continue;
                String fieldNameU=fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
//                System.err.println(fieldNameU+"     "+fb[i].getType()+"=>"+jsonObject.get(fieldName));
                Object value = jsonObject.get(fieldName);
                Method method=null;
                try {
                    method = cla.getMethod("set"+fieldNameU, fb[i].getType());
                } catch (Exception e) {
//                    System.err.println("NOT set"+fieldNameU+" Method");
                    continue;
                }
                if(clazz.equals("java.lang.String")){
                	
                    if(value instanceof JSONNull){
                        value = "";
                    }else if(value!=null){
                    	value = value.toString();
                    }
                }else if(clazz.equals("java.lang.Integer")){
                    if(value instanceof JSONNull){
                        value = null;
                    }
                }else if(clazz.equals("java.util.Date")){
                    if(value instanceof JSONNull){
                        value = null;
                    }else{
                        String date = (String)value;
                        if(StringUtil.isNotEmpty(date)){
                            if(date.indexOf("/")>0){
                                value = DateUtil.parser(date, "yyyy/MM/dd HH:mm");
                            }else if(date.indexOf("T")>0){
                                value = DateUtil.parser(date, JSON_DATE_FORAMT);
                            }else{
                                value = DateUtil.parser(date, "yyyy-MM-dd HH:mm:ss");
                            }
                        }
                    }
                }else if(clazz.equals("java.math.BigDecimal")){
                    if(value instanceof JSONNull){
                        value = new BigDecimal(0);
                    }else{
                        value = BigDecimalUtil.getBigDecimal(value);
                    }
                }else if(clazz.equals("java.lang.Double")){
                    if(value instanceof JSONNull){
                        value = new Double(0);
                    }else{
                        value = new Double(value.toString());
                    }
                }else if(clazz.equals("java.util.List")){
                    if(value instanceof JSONNull){
                        value = null;
                    }
                }
                method.invoke(t, value);
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * json转换List
     * @param jsonArray
     * @param cla
     * @return
     */
    private static <T> List<T> convertToList(JSONArray jsonArray,Class<T> cla){
        List<T> list=new ArrayList<T>();
        if(jsonArray==null||jsonArray.isEmpty()) return list;
        
        try {
            for(int i=0;i<jsonArray.size();i++){ 
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t=convertToObj(jsonObject, cla);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * 获取对象属性字段DeclaredFields（可以处理子类、父类所有字段）
     * @param clazz
     * @return
     */
    private static <T> Field[] getDeclaredFields(Class<T> clazz){
        Field[] fb = clazz.getDeclaredFields();
        Class<?> cla=clazz.getSuperclass();
//        System.err.println(cla);
        if(cla!=Object.class){
            Field[] ft = cla.getDeclaredFields();
            return concat(fb, ft);
        }else{
            return fb;  
        }
    }
    
    /**
     * 数组合并
     * @param first
     * @param second
     * @return
     */
    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
