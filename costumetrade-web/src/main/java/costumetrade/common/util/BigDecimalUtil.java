package costumetrade.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class BigDecimalUtil {
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @return 
	  * 功能：计算两个数的和，结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initAdd(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.add(bd2);
	}
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @return 
	  * 功能：计算两个数的和，结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initAdd(BigDecimal param1, BigDecimal param2) {
		return param1.add(param2);
	}
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @return 
	  * 功能：计算两个数的和，结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal add(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.add(bd2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @return 
	  * 功能：计算两个数的和，结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal add(BigDecimal param1, BigDecimal param2) {
		return param1.add(param2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的和，调用方指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal add(String param1, String param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initAdd(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被加数
	  * @param param2 加数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的和，调用方指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal add(BigDecimal param1, BigDecimal param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initAdd(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @return 
	  * 功能：计算两个数的差，结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initSubtract(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.subtract(bd2);
	}
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @return 
	  * 功能：计算两个数的差，结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initSubtract(BigDecimal param1, BigDecimal param2) {
		return param1.subtract(param2);
	}
	
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @return 
	  * 功能：计算两个数的差，计算结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal subtract(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.subtract(bd2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @return 
	  * 功能：计算两个数的差，计算结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal subtract(BigDecimal param1, BigDecimal param2) {
		return param1.subtract(param2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的差，调用方指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal subtract(String param1, String param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initSubtract(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被减数
	  * @param param2 减数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的差，调用方指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal subtract(BigDecimal param1, BigDecimal param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initSubtract(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @return 
	  * 功能：计算两个数的积，计算结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initMultiply(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.multiply(bd2);
	}
	
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @return 
	  * 功能：计算两个数的积，计算结果保留原始值，不进行舍入
	  *************
	 */
	public static BigDecimal initMultiply(BigDecimal param1, BigDecimal param2,BigDecimal param3) {
		return param1.multiply(param2).multiply(param3);
	}
	/**
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal initMultiply(BigDecimal param1, BigDecimal param2) {
		return param1.multiply(param2);
	}
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @return 
	  * 功能：计算两个数的积，计算结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal multiply(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.multiply(bd2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @return 
	  * 功能：计算两个数的积，计算结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal multiply(BigDecimal param1, BigDecimal param2) {
		return param1.multiply(param2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的积，调用者指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal multiply(String param1, String param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initMultiply(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被乘数
	  * @param param2 乘数
	  * @param scale 计算结果保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的积，调用者指定计算结果保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal mutiply(BigDecimal param1, BigDecimal param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal result = initMultiply(param1, param2);
		return result.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被除数
	  * @param param2 除数
	  * @return 
	  * 功能：计算两个数的商，结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal divide(String param1, String param2) {
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被除数
	  * @param param2 除数
	  * @return 
	  * 功能：计算两个数的商，结果保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal divide(BigDecimal param1, BigDecimal param2) {
		return param1.divide(param2, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * ***********
	  * @param param1 被除数
	  * @param param2 除数
	  * @param scale 计算结算保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的商，调用都指定计算结算保留位数和舍入模式
	  *************
	 */
	public static BigDecimal divide(String param1, String param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		BigDecimal bd1 = new BigDecimal(param1);
		BigDecimal bd2 = new BigDecimal(param2);
		return bd1.divide(bd2, scale, round);
	}
	
	/**
	 * ***********
	  * @param param1 被除数
	  * @param param2 除数
	  * @param scale 计算结算保留的小数位数
	  * @param round 舍入模式
	  * @return 
	  * 功能：计算两个数的商，调用都指定计算结算保留位数和舍入模式
	  *************
	 */
	public static BigDecimal divide(BigDecimal param1, BigDecimal param2, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		return param1.divide(param2, scale, round);
	}
	
	/**
	 * ***********
	  * @param scale 保留小数位数
	  * 功能：校验保留小数位数参数不能为负数
	  *************
	 */
	private static void validateScale(int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
	}
	
	/**
	 * ***********
	  * @param round 舍入模式
	  * 功能：校验舍入模式不能为负数
	  *************
	 */
	private static void validateRound(int round) {
		if (round < 0) {
			throw new IllegalArgumentException("The round must be a positive integer or zero");
		}
	}
	
	/**
	 * ***********
	  * @param param1
	  * @param param2
	  * @return 
	  * 功能：比较两个数的大小，当param1大于param2时返回1，当param1等于param2时返回0，当param1小于param2时返回-1
	  *************
	 */
	private static int compareTo (BigDecimal param1, BigDecimal param2) {
		return param1.compareTo(param2);
	}

	/**
	 * ***********
	  * @param param1
	  * @param param2
	  * @return 
	  * 功能：比较两个数的大小，当param1小于param2时返回true，否则返回false
	  *************
	 */
	public static Boolean lessThan(BigDecimal param1, BigDecimal param2) {
		return -1 == compareTo(param1, param2);
	}
	
	/**
	 * ***********
	  * @param param1
	  * @param param2
	  * @return 
	  * 功能：比较两个数的大小，当param1大于param2时返回true，否则返回false
	  *************
	 */
	public static Boolean greaterThan(BigDecimal param1, BigDecimal param2) {
		return 1 == compareTo(param1, param2);
	}
	
	/**
	 * ***********
	  * @param param1
	  * @param param2
	  * @return 
	  * 功能：比较两个数的大小，当param1等于param2时返回true，否则返回false
	  *************
	 */
	public static Boolean equal(BigDecimal param1, BigDecimal param2) {
		return 0 == compareTo(param1, param2);
	}
	
	/**
	 * ***********
	  * @param param1
	  * @param scale
	  * @param round
	  * @return 
	  * 功能：调用者指定BigDecimal类型数据保留的小数位数和舍入模式
	  *************
	 */
	public static BigDecimal round(BigDecimal param1, int scale, int round) {
		validateScale(scale);
		validateRound(round);
		return param1.setScale(scale, round);
	}
	
	/**
	 * ***********
	  * @param param1
	  * @return 
	  * 功能：对BigDecimal保留两位小数，舍入模式为ROUND_HALF_UP
	  *************
	 */
	public static BigDecimal round(BigDecimal param1) {
		return param1.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 将BigDecimal转换为2位小数输出
	 * @param param1
	 * @return
	 */
	public static String formateTwoPointStr(BigDecimal param1){
		return new DecimalFormat("#0.00").format(param1.doubleValue());
	}
	public static String formateThreePointStr(BigDecimal param1){
		return new DecimalFormat("#0.000").format(param1.doubleValue());
	}
	
	public static String formateFourPointStr(BigDecimal param1){
		return new DecimalFormat("#0.0000").format(param1.doubleValue());
	}
	
	/**
	 * 将BigDecimal转化为任意位小数格式输出
	 * @param param1 参数值
	 * @param width 小数位数
	 * @return
	 */
	public static String formateAnyPointStr(BigDecimal param1,int width){
		return formateAnyPointStr(param1.doubleValue(),width);
	}
	/**
	 * 将double转化为任意位小数格式输出
	 * @param param1 参数值
	 * @param width 小数位数
	 * @return
	 */
	public static String formateAnyPointStr(double param1,int width){
		StringBuilder formateSB = new StringBuilder("#");
		if(width>0){
			formateSB.append(".");
			for(int i=0;i<width;++i){
				formateSB.append("0");
			}
		}
		return new DecimalFormat(formateSB.toString()).format(param1);
	}
	
	public static BigDecimal getBigDecimal(Object value) {  
        BigDecimal ret = null;  
        if(value != null) {  
            if( value instanceof BigDecimal) {  
                ret = (BigDecimal) value;  
            } else if(value instanceof String) {  
                ret = new BigDecimal((String) value);  
            } else if(value instanceof BigInteger) {  
                ret = new BigDecimal((BigInteger) value);  
            } else if(value instanceof Number) {  
                ret = new BigDecimal(((Number)value).doubleValue());  
            } else {  
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");  
            }  
        }  
        return ret;  
    }
	
	/**
	 * 取小数整数部分，不四舍五入
	 * @param param
	 * @return
	 */
	public static BigDecimal getIntegralPart(BigDecimal param){
		return param.setScale(0, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 判断对象是否为整数
	 * @param param
	 * @return
	 */
	public static boolean isIntegerValue(BigDecimal param) {  
		  return param.signum() == 0 || param.scale() <= 0 || param.stripTrailingZeros().scale() <= 0;  
	} 
}