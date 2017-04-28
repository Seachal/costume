package costumetrade.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;


/**
 *  时间处理工具类
 * @author yh.yu
 *
 */
public class DateUtil {
	/*** 1毫秒 */
	public static final long ONE_MILLI_SECOND=1l;
	/*** 1秒 */
	public static final long ONE_SECOND=ONE_MILLI_SECOND  *1000;
	/*** 1分钟*/
	public static final long ONE_MINUTE=ONE_SECOND  *60;
	/*** 1小时 */
	public static final long ONE_HOUR=ONE_MINUTE *60;
	/*** 1天 */
	public static final long ONE_DAY=ONE_HOUR *24;
	/*** 1月 */
	public static final long ONE_MONTH=ONE_DAY  *30;
	/*** 1年 */
	public static final long ONE_YEAR=ONE_MONTH *12;
	/*** 1世纪 */
	public static final long ONE_CENTURY=ONE_YEAR *100;

	public static final String ISO_DATE_FORMAT ="yyyy-MM-dd HH:mm:ss.SSS EEE Z";

	public static final String ymdhms_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String ymdhm_DATE_FORMAT = "yyyy-MM-dd HH:mm";

	public static final String ymd_E_DATE_FORMAT = "yyyy-MM-dd EEE";

	public static final String ymd_DATE_FORMAT = "yyyy-MM-dd";

	public static final String hms_DATE_FORMAT = "HH:mm:ss";

	public static final String hm_DATE_FORMAT = "HH:mm";
	
	public static final String mdy_E_DATE_FORMAT_US = "MM/dd/yyyy EEE";

	public static final String mdy_DATE_FORMAT_US
	= "MM/dd/yyyy";

	public static final String ymdhms_TIME_STAMP_FORMAT
	= "yyyyMMddHHmmss";

	public static final String ymdhm_TIME_STAMP_FORMAT
	= "yyyyMMddHHmm";

	public static final String ymd_TIME_STAMP_FORMAT
	= "yyyyMMdd";

	public static final String hms_TIME_STAMP_FORMAT
	= "HHmmss";

	public static final String chinese_ymdhms_E_DATE_FORMAT
	= "yyyy年MM月dd日 HH:mm:ss EEE";

	public static final String chinese_ymdhms_DATE_FORMAT
	= "yyyy年MM月dd日 HH:mm:ss";

	public static final String chinese_ymd_DATE_FORMAT
	= "yyyy年MM月dd日";
	
	/**
	 * 取系统当前日期的开始时间（yyyy-MM-dd 00:00:00:000）
	 * @return
	 */
	public static Date getCurrentDateBegin(){
		Date date = new Date();
		return zeroConvertTime(date);
	}
	/**
	 * 将日期后的时间填满 变成(yyyy-MM-dd 23:59:59:999)
	 * @return
	 */
	public static Date getCurrentDateEnd(){
		Date date = new Date();
		return totalConvertTime(date);
	}
	/**
	 * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
	 * @param fullDate
	 * @return
	 */
    public static final Date zeroConvertTime(Date fullDate){
        Calendar cal = Calendar.getInstance();       
        cal.setTime(fullDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
    /**
     * 将日期后的时间填满 变成(yyyy-MM-dd 23:59:59:999)
     * @param fullDate
     * @return
     */
    public static final Date totalConvertTime(Date fullDate){
    	Calendar cal = Calendar.getInstance();       
    	cal.setTime(fullDate);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MINUTE, 59);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MILLISECOND, 999);
    	return cal.getTime();
    }
    /**
     * 获取当前日期对用的月份的第一天
     * @param fullDate
     * @return
     */
    public static final Date getFirstDayOfOneMonth(Date fullDate){
        Calendar cal = Calendar.getInstance();       
        cal.setTime(fullDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
	}
    /**
     * 获取当前日期对应的月份的最后一天
     * @param fullDate
     * @return
     */
    public static final Date getLastDayOfOneMonth(Date fullDate){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(fullDate);     
        cal.add(Calendar.MONTH, 1);  //当前月加1 就是下个月     
        cal.set(Calendar.DATE, 1);   //下个月的第一天    
        cal.add(Calendar.DATE, -1);  //下个月的前一天就是上个月的最后一天  	    
    	return cal.getTime();
    }

    /**
     * 获取当前时间 如 2010-08-24 18:45:45
     * @return
     */
    public static final String getCurrentDateStr(){
    	
    	return ymdhmsFormat(new Date());
    }
    
    /**
     * 将指定时间转换为ymdhms格式 如：20100824185707
     * @param date
     * @return
     */
    public static final String ymdhmsTimeStampFormat(Date date){
        
        return DateFormatUtils.format(date, ymdhms_TIME_STAMP_FORMAT);
    }
    
    /**
     * 将指定时间转换为ymdhms格式 如：2010-08-24 18:57:07
     * @param date
     * @return
     */
    public static final String ymdhmsFormat(Date date){
    	
        return DateFormatUtils.format(date, ymdhms_DATE_FORMAT);
    }
    /**
     * 获取中文显示的指定时间
     * @param date
     * @return
     */
    public static final String chineseFormat(Date date){
    	
    	return DateFormatUtils.format(date, chinese_ymdhms_DATE_FORMAT);
    }
    /**
     * 获取中文显示的当前时间
     * @return
     */
    public static final String getCurrentChineseDate(){

    	return chineseFormat(new Date());
    }

    /**
     * 将日期类型为 2010-08-25 08:53:56 格式的字符数据转换为日期类型
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static final Date praseDate(String dateStr) throws ParseException {
		return DateUtils.parseDate(dateStr,"yyyy-MM-dd HH:mm:ss");
	}
    
    /**
     * 将日期类型为 2010-08-25 08:53:56 格式的字符数据转换为日期类型
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static final Date praseDateYYYY_MM_DD(String dateStr) throws ParseException {
		return DateUtils.parseDate(dateStr,"yyyy-MM-dd");
	}
    /**
     * 将中文类型的字符数据转换为日期类型 如 2010年8月25日 08:56:05
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static final Date praseChineseDate(String dateStr) throws ParseException {

    	return DateUtils.parseDate(dateStr,"yyyy年MM月dd日 HH:mm:ss");
    }
    /**
     * 加减时间(当amount为正数是为加，否则为减)
     * @param field  添加的时间域
     * @param date 要添加的时间
     * @param amount 数据量
     * @return
     */
    public static final Date addDate(int field,Date date,int amount) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(field, amount);
    	return cal.getTime(); 
    }
    /**
     * 加减天数
     * @param date
     * @param days
     * @return
     */
    public static final Date addDay(Date date,int days){
    	return addDate(Calendar.DAY_OF_YEAR, date, days);
    }
    /**
     * 加减月数
     * @param date
     * @param months
     * @return
     */
    public static final Date addMonth(Date date,int months){
    	return addDate(Calendar.MONTH, date, months);
    }
    /**
     * 加减年数
     * @param date
     * @param years
     * @return
     */
    public static final Date addYear(Date date,int years){
    	return addDate(Calendar.YEAR, date, years);
    }
    /**
     * 加减分钟数
     * @param date
     * @param minutes
     * @return
     */
    public static final Date addMinute(Date date,int minutes){
    	return addDate(Calendar.MINUTE , date, minutes);
    }
    /**
     * 加减秒数
     * @param date
     * @param seconds
     * @return
     */
    public static final Date addSecond(Date date,int seconds){
    	return addDate(Calendar.SECOND , date, seconds);
    }
    /**
     * 取得指定日期的上一个月的日期点
     * @param date
     * @return
     */
    public static final Date getLastMonth(Date date){
    	return addMonth(date, -1);
    }
    /**
     * 取得指定日期的下一个月的日期点
     * @param date
     * @return
     */
    public static final Date getNextMonth(Date date){
    	return addMonth(date, -1);
    }
    /**
     * 返回指定日期的星期 1 星期一~7 星期日
     * @param date
     * @return
     */
    public static final int getWeek(Date date){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
    	return week==0?7:week;
    }
    
    /**
     * ***********
      * @param date
      * @return
      * @throws ParseException 
      * 功能：取某个时间的小时,未验证时间格式
      *************
     */
    public static final String getHourOfDay(String date) throws ParseException {
    	if(null == date)
    		return null;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(praseDate(date));
    	
    	return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }
    /**
     * 返回指定日期的星期  星期一~星期日
     * @param date
     * @return
     */
    public static final String getWeekStr(Date date){
    	String weeks[]={"日","一","二","三","四","五","六"};
    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return "星期"+weeks[cal.get(Calendar.DAY_OF_WEEK)-1];
    }
    /**
     * 两个时间的间隔（计算的数据如果不是整形会被四舍五入，请选择适当的间隔单元）
     * @param date1
     * @param date2
     * @param unit 间隔单元 如 ONE_HOUR(一小时) ONE_DAY(一天) ONE_YEAR(一年)
     * @return
     */
    public static long compare(Date date1,Date date2,long unit) {
    	Long num1 = date1.getTime();
    	Long num2 = date2.getTime();
    	return Math.round((num1-num2)/unit);
    }
    /**
     * 两个时间的间隔的天数（计算的数据如果不是整形会被四舍五入）
     * @param date1
     * @param date2
     * @return
     */
    public static long compareToDay(Date date1,Date date2) {
    	return compare(date1, date2, ONE_DAY);
    }
    /**
     * @Description:比较两个时间间隔的时间，取地板值
     * @param date1
     * @param date2
     * @param unit
     * @return
     */
    public static long compareToFloor(Date date1,Date date2,long unit) {
    	Long num1 = date1.getTime();
    	Long num2 = date2.getTime();
    	return (long) Math.floor((num1-num2)/unit);
    }
    /**
     * @Description:比较两个时间间隔的时间，取天花板值
     * @param date1
     * @param date2
     * @param unit
     * @return
     */
    public static long compareToCell(Date date1,Date date2,long unit) {
    	Long num1 = date1.getTime();
    	Long num2 = date2.getTime();
    	return (long) Math.ceil((num1-num2)/unit);
    }
    
    public static String ymdFormat(Date date){
    	
    	  return DateFormatUtils.format(date, ymd_DATE_FORMAT);
    }
    
    /**
     * 
     * 将阿拉伯数字日期转成中文日期
     * @param date
     * @return
     */
    public static String dateToChineseDate(Date date){
    	String chineseYmd = "";
    	if(null == date){
    		return chineseYmd;
    	}
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(date);
    	String year = String.valueOf(calendar.get(Calendar.YEAR));
    	String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
    	String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    	
    	String[] strArray = year.split("");
    	for(int i = 0; i < strArray.length; i++){
    		if(!strArray[i].equals("")){
    			chineseYmd += DateUtil.ConverNumToCh(strArray[i]);
    		}
    		
    	}
    	chineseYmd +="年";
    	int index = 0;
    	strArray = month.split("");
    	for(int i = 0; i < strArray.length; i++){
    		if(index==1){
    			chineseYmd+="十";
    		}
    		if(!strArray[i].equals("")){
    			index++;
    			chineseYmd += DateUtil.ConverNumToCh(strArray[i]);
    		}
    		
    	}
    	chineseYmd+="月";
    	index = 0;
    	strArray = day.split("");
    	for(int i = 0; i < strArray.length; i++){
    		if(index==1){
    			chineseYmd+="十";
    		}
    		if(!strArray[i].equals("")){
    			index++;
    			chineseYmd += DateUtil.ConverNumToCh(strArray[i]);
    		}
    	}
    	chineseYmd+="日";
    	return chineseYmd;
    }
    
    public static String ConverNumToCh(String numStr){
    	Integer key = Integer.valueOf(numStr);
    	switch (key) {
			case 0:
				numStr = "零";
				break;
			case 1:
				numStr = "一";
				break;
			case 2:
				numStr = "二";
				break;
			case 3:
				numStr = "三";
				break;
			case 4:
				numStr = "四";
				break;
			case 5:
				numStr = "五";
				break;
			case 6:
				numStr = "六";
				break;
			case 7:
				numStr = "七";
				break;
			case 8:
				numStr = "八";
				break;
			case 9:
				numStr = "九";
				break;
		}
    	return numStr;
    }
    
    /**
     * Long类型时间戳，转换成 yyyy-MM-dd HH:mm:ss 格式
     * @param millSec
     * @return
     */
    public static String converLongToDate(long millis){

        return DateFormatUtils.format(millis, ymdhms_DATE_FORMAT);
    }
    
    /**
     * 获得时间开始区间
     * 格式: x天x小时x分x秒
     * @return
     */
    public static String getDuration(Date begin, Date end) {
        long duration = end.getTime()-begin.getTime();
        
        if(duration<0){
            throw new IllegalArgumentException("开始时间不能大于结束时间");
        }
        
        if(duration==0){
            return "0秒";
        }
        
        StringBuilder builder = new StringBuilder();
        
        long day = duration / ONE_DAY;
        if(day>0){
            builder.append(day).append("天");
            duration =  duration - day*ONE_DAY;
        }
 
        long hour = duration / ONE_HOUR;
        if(builder.length()>0||hour>0){
            builder.append(hour).append("时");
        }
        duration =  duration - hour*ONE_HOUR;
        
        long minute = duration / ONE_MINUTE;
        if(builder.length()>0||minute>0){
            builder.append(minute).append("分");
        }   
        duration =  duration - minute*ONE_MINUTE;
        
        long sec = duration / ONE_SECOND;
        if(builder.length()>0||sec>0){
            builder.append(sec).append("秒");
        }       
        return builder.toString();
    }
    
    public static String formatDate(Date date, String pattern){
        return DateFormatUtils.format(date, pattern);
    }
    
    public static Date parser(String date,String format){
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 计算天数
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) { 	     
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;            
        return days;   
   }
	/**
	 * 返回两个时间的相差时间 精确到分钟
	 * @param early
	 * @param late
	 * @return xx天xx小时xx分钟
	 */
	public static final String daysBetweenAccurateMin(Date early, Date late){
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		
		long diff = late.getTime() - early.getTime();
		long day = diff/nd;//计算差多少天
		long hour = diff%nd/nh;//计算差多少小时
		long min = diff%nd%nh/nm;//计算差多少分钟
		if(day < 1){
			day = 0;
		}if(hour < 1){
			hour = 0;
		}if(min < 1){
			min = 0;
		}
		return day+"天"+hour+"小时"+min+"分钟";
	}
	
}
