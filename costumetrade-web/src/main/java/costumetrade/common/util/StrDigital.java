package costumetrade.common.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 判断字符串与数字关系
 * @author luchunlong
 * @date 2017-03-13
 *
 */

public class StrDigital {
	
	/**
	 * 去掉整数字符串中最前面的0，并转为整数
	 * @param IntStr
	 * @return
	 */
	public static int strToInt(String IntStr){
		String newIntStr=IntStr.replaceFirst("^0", "");
		return Integer.parseInt(newIntStr);
	}
	
	/**
	 * 去掉整数字符串中最前的所有的0
	 * @param IntStr
	 * @return
	 */
	public static String strFilterFrontZero(String IntStr){
		String newIntStr=IntStr.replaceFirst("^0", "");
		return newIntStr;
	}
	
	
	 /**
	  * 判断字符串是不是数字
	  * @param str
	  * @return
	  */
	 public static boolean isNumeric(String str){ 
	  if (null == str || "".equals(str)) {  
	        return false;  
	    } 
  	   Pattern pattern = Pattern.compile("[0-9]*"); 
  	   return pattern.matcher(str).matches();  
  	}
	 
	 /**
	  * 判断字符串是不是整数
	  * @param str
	  * @return
	  */
	 public static boolean isInteger(String str) {  
	    if (null == str || "".equals(str)) {  
	        return false;  
	    }  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	}  
	
   /**
    * 判断字符串是不是小数
    * @param str
    * @return
    */
   public static boolean isDouble(String str) {  
	    if (null == str || "".equals(str)) {  
	        return false;  
	    }  
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");  
	    return pattern.matcher(str).matches();  
	} 
   
}