/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年6月21日
 */
package costumetrade.common.util;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串拼音
 * 
 * @author liyb
 * @version PingyinUtil.java,2016年6月21日 下午2:24:31
 */
public class PingyinUtil {
	
    private static final HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	
	static{
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}
	
	/**
	 * 提取每个汉字的拼音
	 */
	public static String convert(String word) {
		try {
			return PinyinHelper.toHanYuPinyinString(word, defaultFormat, "", false);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new RuntimeException("汉语转拼音异常", e);
		}
	}

	/**
	 * 提取每个汉字的首字母
	 */
	public static String getPinYinHeadChar(String str) {
		StringBuilder convert = new StringBuilder();
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// 提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert.append(pinyinArray[0].charAt(0));
			} else {
				convert.append(word);
			}
		}
		return convert.toString();
	}

    /**
     * 提取每个汉字的拼音
     */
    public static String hanziToPinyin(String word) {
        String convert = ""; 
        //创建汉语拼音处理类  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        try {
            //输出设置，大小写，音标方式  
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            convert = PinyinHelper.toHanYuPinyinString(word, defaultFormat, "", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convert;
    }
    
    /**
     * 提取每个汉子的大写拼音字母，以逗号隔开
     * @param str
     * @return 如：WANG,XING,LIN
     */
    public static String hanziToPinyinChar(String str){
        String convert = ""; 
        char[] pinyinArray = str.toCharArray(); 
        for (char hanzi:pinyinArray) { 
            if(StringUtil.isNotEmpty(convert)){
                convert = convert+","+PingyinUtil.hanziToPinyin(hanzi+"");
            }else{
                convert = hanziToPinyin(hanzi+"");
            }
        } 
        return convert;
    }
    
    /**
     * 去除多余空格，返回大写拼音，以逗号隔开
     * @param str
     * @return
     */
    public static String skipEmptyPinyin(String str){
        String convert = ""; 
        convert = str.replaceAll(" ", ",").toUpperCase();
        String array[] = convert.split(",");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if(StringUtil.isNotEmpty(array[i])){
                list.add(array[i]);
            }
        }
        convert = "";
        for (String py:list) {
            if(StringUtil.isNotEmpty(convert)){
                convert = convert+","+py;
            }else{
                convert = py;
            }
        }
        return convert;
    }
}
