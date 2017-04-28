package costumetrade.common.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符处理公共类
 * @author yh.yu
 *
 */
public class StringUtil {
	/***空字符串*/
	public static final String EMPTY_STRING = "";
	
	
	public static String format(String str,Object ... args)
	{
		return format(str,java.util.regex.Pattern.compile("\\{(\\d+)\\}"),args);
	}
	/**
	 * 字符串参数格式化
	 * @param str
	 * @param args
	 * @return
	 */
	public static String format(final String str,Pattern pattern,Object ... args){
		// 这里用于验证数据有效性
		if (str == null || "".equals(str))
			return "";
		if (args.length == 0) {
			return str;
		}

		String result = str;

		// 这里的作用是只匹配{}里面是数字的子字符串
		java.util.regex.Pattern p = pattern;
		java.util.regex.Matcher m = p.matcher(str);

		while (m.find()) {
			// 获取{}里面的数字作为匹配组的下标取值
			int index = Integer.parseInt(m.group(1));
			// 这里得考虑数组越界问题，{1000}也能取到值么？？
			if (index < args.length && args[index]!=null) {
				// 替换，以{}数字为下标，在参数数组中取值
				result = result.replace(m.group(), args[index].toString());
			} else {
				result = result.replace(m.group(), "");
			}
		}
		return result;
	}
	/**
	 * 检测是否是空字符串
	 * @param str
	 * @return
	 */
	public static final boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	/**
	  * 汉语转拼音
	  * @param src
	  * @return
	  */
	 public static String getPingYin(String src) {  
		  
	        char[] t1 = null;  
	        t1 = src.toCharArray();  
	        String[] t2 = new String[t1.length];  
	        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
	          
	        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
	        String t4 = "";  
	        int t0 = t1.length;  
	        try {  
	            for (int i = 0; i < t0; i++) {  
	                // 判断是否为汉字字符  
	                if (java.lang.Character.toString(t1[i]).matches(  
	                        "[\\u4E00-\\u9FA5]+")) {  
	                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
	                    t4 += t2[0];  
	                } else  
	                    t4 += java.lang.Character.toString(t1[i]);  
	            }  
	            // System.out.println(t4);  
	            return t4;  
	        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
	            e1.printStackTrace();  
	        }  
	        return t4;  
	    }
	/**
	 * 检测指定字符串是否不是空串
	 * @param str
	 * @return
	 */
	public static final boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	/**
	 * 检测指定字符串是否为空串（去除前后导全半角空格）
	 * @param str
	 * @return
	 */
	public static final boolean isBlank(String str) {
		if (isNotEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				if ((Character.isWhitespace(str.charAt(i)) == false)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 检测指定字符串是否不为空串
	 * @param str
	 * @return
	 */
	public static final boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	/**
	 * 将为NULl的字符串转换为空字符串
	 * @param str
	 * @return
	 */
	public static final String trimNull(String str) {
		return null==str?EMPTY_STRING:str;
	}
	/**
	 * 如果字符串为Null则将其转换为对应的默认串
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static final String setDefaultIfNull(String str,String defaultStr) {
		return null==str?trimNull(defaultStr):str;
	}
	/**
	 * 如果字符串为Null则将其转换为对应的默认串""
	 * @param str
	 * @return
	 */
	public static final String setDefaultIfNull(String str) {
		return setDefaultIfNull(str, EMPTY_STRING);
	}
	/**
	 * 如果字符串为空字符串则将其转换为对应的默认串
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static final String setDefaultIfEmpty(String str,String defaultStr) {
		return isEmpty(str)?trimNull(defaultStr):str;
	}
	/**
	 * 如果字符串为空字符串则将其转换为对应的默认串""
	 * @param str
	 * @return
	 */
	public static final String setDefaultIfEmpty(String str) {
		return setDefaultIfEmpty(str, EMPTY_STRING);
	}
	/**
	 * 如果字符串为空串则将其转换为对应的默认串
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static final String setDefaultIfBlank(String str,String defaultStr) {
		return isBlank(str)?trimNull(defaultStr):str;
	}
	/**
	 * 如果字符串为空串则将其转换为对应的默认串""
	 * @param str
	 * @return
	 */
	public static final String setDefaultIfBlank(String str) {
		return setDefaultIfBlank(str, EMPTY_STRING);
	}
	/**
	 * 检测是不是数字化的字符串
	 * @param str
	 * @return
	 */
	public static final boolean isNumer(String str) {
		if (isEmpty(str)) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 转换为半角字符串
	 * 任意字符串转换为<p>半角</p>字符串
	 * 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * @param input
	 * @return
	 */
	public static final String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i< c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i]> 65280&& c[i]< 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}
	/**
	 * 转全角的函数(SBC case)
	 * 任意字符串转	全角字符串
	 * 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * @param input
	 * @return
	 */
	public static final String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i< c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i]< 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}
	/**
	 * 去除前后导空格（包括半角和全角的）
	 * @param input
	 * @return
	 */
	public static final String trim(String input) {
		return input.replaceAll("(^[ |　]*|[ |　]*$)","");
	}
	/**
	 * 去除所有的空格（包括半角和全角的）
	 * @param input
	 * @return
	 */
	public static final String trimAll(String input) {
		return input.replaceAll(" ","").replace("　", "");
	}


	/**
	 * 功能：
	 *
	 * @author yh1.yu
	 * @param linkMobile
	 * @return
	 */
	public    static final  String getLinkMobileShow(String mobile) {
		String linkMobileShow = "";
		if (StringUtil.isNotBlank(mobile) && mobile.length() > 3) {
			linkMobileShow = mobile.substring(0, 3)	+ "****" 
				+ mobile.substring(mobile.length() - 4,mobile.length());
		}
		return linkMobileShow;
	}
	/**
	 * 功能：
	 *
	 * @author yh1.yu
	 * @param userName
	 * @return
	 */
	public static final String getUserNameShow(String memberName){
		String  userNameShow="";
		if (StringUtil.isNotBlank(memberName) && memberName.length() >= 2) {
			userNameShow = memberName.substring(0, 1)	+ "**";
		}
		return userNameShow;
	}
	/**
	 * @param home
	 * @return
	 */
	public static final String get6Show(String showString){
		String  userNameShow="";
		if (StringUtil.isNotBlank(showString) && showString.length() >= 6) {
			userNameShow = showString.substring(0, showString.length()-6)	+ "******";
		}else {
			return "******";
		}
		return userNameShow;
	}
	
	public static final String get4Show(String showString){
		String  userNameShow="";
		if (StringUtil.isNotBlank(showString) && showString.length() >= 4) {
			userNameShow = showString.substring(0, showString.length()-4)	+ "****";
		}else {
			return "****";
		}
		return userNameShow;
	}
	
	public static final String get3MiddleShow(String pcode){
		String  userNameShow="";
		if (StringUtil.isNotBlank(pcode) && pcode.length() >= 6) {
			userNameShow = pcode.replaceAll(pcode.substring(3,6),"***");
		}else {
			return "***";
		}
		return userNameShow;
	}
	
	/**
     * 生成交易流水号，规则:Yyyymmddhh24miss+6位自生成序号
     * @return
     */
    public static final String generatorSerialNumber(){
        String serialNumber="";
        int num = generateSixCode();
        serialNumber=DateUtil.ymdhmsTimeStampFormat(new Date());
        return serialNumber+num;
    }
	
	/**
     * 生成六位随机数字
     * @return
     */
    private static int generateSixCode(){
        Random random = new Random();
        int x = random.nextInt(899999);
        int y = x+100000;
        return y;
    }
    
    /**
     * 根据身份证号码取出生日期
     * @param idCard
     * @return
     */
    public static final String getBirthdate(String idCard){
    	Integer idLength = idCard.length();
    	if(idLength==18){
    		return idCard.substring(6,14);
    	}else if(idLength==15){
    		return "19"+idCard.substring(6,12);
    	}
    	return "00000000";
    }
    
    /**
     * 字符串如果是空值，则返回空字符串
     * @param str
     * @return
     */
    public static final String nullToStr(Object obj){
    	return null == obj || "".equals(obj) ? "" : obj.toString();
    }
    
    /**
     * 转换文件大小
     * @param size
     * @return
     */
    public static String FormetFileSize(String size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        int fileS = Integer.parseInt(size); 
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
    
    /**
     * 生成字母+数字的随机数
     * @param length 长度
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母 
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    
	public static void main(String[] args) {
		System.out.println(get3MiddleShow("浙江惠瀜网络科技有限公司"));
		System.out.println(getUserNameShow("余勇辉"));
		System.out.println(get4Show("测试赛帅帅2228大幅度反对法"));
		System.err.println("压缩前:"+FormetFileSize("8869034")+"\r\n压缩后："+FormetFileSize("1790619"));
	}


}
