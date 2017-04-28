/**
 * 
 */
package costumetrade.common.util;

import static costumetrade.common.util.DateFormatUtils.yyyyMMddHHmmss_FORMAT;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yh.yu
 * @author zhouyq
 */
public class OrderNoGenerator {
	
	private static final String GLOBAL_PREFIX="vx";
	
	/**
	 *  @author yh.yu
	  * @param prefix 订单号前缀
	  * @param length 随机数位数
	 * @return
	 */
	public static String generate(String prefix) {
		return generate(prefix, 3);
	}
	
	/**
	 * 
	 *  @author yh.yu
	  * @param prefix 订单号前缀
	  * @param length 随机数位数
	  * @return 
	  * 功能：生成订单号
	 * @return
	 */
	public static String generate(String prefix, int length) {
		prefix=StringUtils.trim(GLOBAL_PREFIX+prefix);
		length=(length<=0)?6:length;		//传输错误时采用6bit随机数
		return prefix+yyyyMMddHHmmss_FORMAT.format(System.currentTimeMillis())+RandomStringUtils.randomNumeric(length);
	}

	
}
