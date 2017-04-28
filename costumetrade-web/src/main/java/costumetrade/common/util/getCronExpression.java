package costumetrade.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CronExpression设置表达式
 * @author luchunlong
 * @date 2017-03-14
 */
public class getCronExpression {
	
	/**
     * 根据任务启动间隔和启动时间，设置任务触发时间
     * @param taskCode      任务编号
     * @param startInterval 启动时间间隔 
     * 备注：startInterval值可能为 0(当天);1(每天);30(1个月)；60(2个月)；90(3个月);......;以及小数
     * @param startTime     启动时间
     * @return
     */
    public static String getCronExpression(String startInterval,Date startTime){
    	String cronExpression="0 * * * * ?";//默认每1分钟触发;格式为 "秒 分钟 小时 天 月  星期";
    	String startIntervalIntStr="";      //启动时间间隔字符串(整数部分)
        String startIntervalDecimalsStr=""; //启动时间间隔字符串(小数部分)
        boolean isInteger=false;  //是否是整数标志
        int startIntervalYear=0;  //相隔年份
	    int startIntervalMonth=0; //相隔月份
	    int startIntervalDay=0;   //相隔天数
	    int startIntervalHour=0;  //相隔小时
	    int startIntervalMinute=0;//相隔分
	    int startIntervalSecond=0;//相隔秒
    	String startTimeYear="";  //启动时间年份
    	String startTimeMonth=""; //启动时间月份
    	String startTimeDay="";   //启动时间天数
    	String startTimeHour="";  //启动时间小时
    	String startTimeMinute="";//启动时间分
    	String startTimeSecond="";//启动时间秒
    	boolean startTimeMonthFlag=true; //启动时间月份标记,默认任何时刻都可以调用
    	boolean startTimeDayFlag=true;   //启动时间天数标记
    	boolean startTimeHourFlag=true;  //启动时间小时标记
    	boolean startTimeMinuteFlag=true;//启动时间分标记
    	boolean startTimeSecondFlag=true;//启动时间秒标记
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
    	String[] startTimeArray=sdf.format(startTime).toString().split(":");
    	if(startTimeArray.length==6){
		  startTimeYear=StrDigital.strFilterFrontZero(startTimeArray[0]);//过滤字符串中最前面0
    	  startTimeMonth=StrDigital.strFilterFrontZero(startTimeArray[1]);
    	  startTimeDay=StrDigital.strFilterFrontZero(startTimeArray[2]);
    	  startTimeHour=StrDigital.strFilterFrontZero(startTimeArray[3]);
    	  startTimeMinute=StrDigital.strFilterFrontZero(startTimeArray[4]);
    	  startTimeSecond=StrDigital.strFilterFrontZero(startTimeArray[5]);
    	}

	   isInteger=StrDigital.isInteger(startInterval); //判断是不是整数
	   if(isInteger){
		   startIntervalIntStr=startInterval;
	   }else{
		   String[] startIntervalArray=startInterval.split(".");
	       if(startIntervalArray.length>1){
	    	   startIntervalIntStr=startIntervalArray[0];
	    	   startIntervalDecimalsStr="0."+startIntervalArray[1];//小数
	       }
	   }

	   if(startIntervalIntStr!=null && !"".equals(startIntervalIntStr)){ //整数部分非空
		   int startIntervalInt=0;
		   startIntervalInt=Integer.parseInt(startIntervalIntStr); //整数部分

		   startIntervalYear=startIntervalInt/365; //年份
		   startIntervalMonth=startIntervalInt/30; //月份
		   startIntervalDay=startIntervalInt%30;   //天数
		   
		   if(startIntervalDecimalsStr!=null && !"".equals(startIntervalDecimalsStr)){ //小数部分非空
			   double startIntervalDecimals=0.0;
	    	   startIntervalDecimals=Double.parseDouble(startIntervalDecimalsStr);//小数部分

	    	   String startIntervalHourIntStr="";          //小时字符串(整数部分)
			   String startIntervalHourDecimalsStr="";     //小时字符串(小数部分)
			   String startIntervalDecimalsMultTempStr=""; //中间变量
			   String startIntervalHourDecimalsMultStr="";
	    	   double startIntervalDecimalsMult=0.0; //小数乘法：整数部分为小时；小数部分为分+秒
	    	   startIntervalDecimalsMult=startIntervalDecimals*24; //*24小时
			   startIntervalDecimalsMultTempStr=String.valueOf(startIntervalDecimalsMult);
			   DecimalFormat df = new DecimalFormat("0.00"); 
			   startIntervalHourDecimalsMultStr=df.format(startIntervalDecimalsMultTempStr); //转化为格式"X.XX"字符串
			   String[] startIntervalDecimalsMultArray=startIntervalHourDecimalsMultStr.split(".");
			   if(startIntervalDecimalsMultArray.length>1){
				  startIntervalHourIntStr=startIntervalDecimalsMultArray[0]; 
		    	  startIntervalHourDecimalsStr="0."+startIntervalDecimalsMultArray[1];
		       }
			   
			   if(startIntervalHourIntStr!=null && !"".equals(startIntervalHourIntStr)){  //小时整数部分非空
				   startIntervalHour=Integer.parseInt(startIntervalHourIntStr);//小时
				   
				   if(startIntervalHourDecimalsStr!=null && !"".equals(startIntervalHourDecimalsStr) && !"0.00".equals(startIntervalHourDecimalsStr)){ 
					   double startIntervalHourDecimals=0.0;
					   startIntervalHourDecimals=Double.parseDouble(startIntervalHourDecimalsStr);//小数部分
					   
					   String startIntervalMinuteIntStr="";   //分字符串(整数部分)
					   String startIntervalMinuteDecimalsStr="";//分字符串(小数部分)
					   String startIntervalHourDecimalsMultTempStr="";
					   String startIntervalMinuteDecimalsMultStr="";
					   double startIntervalHourDecimalsMult=0.0; //小数乘法：整数部分为分；小数部分为秒+厘
					   startIntervalHourDecimalsMult=startIntervalHourDecimals*60;
					   startIntervalHourDecimalsMultTempStr=String.valueOf(startIntervalHourDecimalsMult);
					   startIntervalMinuteDecimalsMultStr=df.format(startIntervalHourDecimalsMultTempStr);//转化为格式"X.XX"字符串
					   String[] startIntervalMinuteDecimalsArray=startIntervalMinuteDecimalsMultStr.split(".");
					   if(startIntervalMinuteDecimalsArray.length>1){
						  startIntervalMinuteIntStr=startIntervalMinuteDecimalsArray[0];
					      startIntervalMinuteDecimalsStr="0."+startIntervalMinuteDecimalsArray[1];
					    }
					   
					   if(startIntervalMinuteIntStr!=null && !"".equals(startIntervalMinuteIntStr)){  
						   startIntervalMinute=Integer.parseInt(startIntervalMinuteIntStr);//分
						   
						   if(startIntervalMinuteDecimalsStr!=null && !"".equals(startIntervalMinuteDecimalsStr) && !"0.00".equals(startIntervalMinuteDecimalsStr)){ 
							   double startIntervalMinuteDecimals=0.0;
							   startIntervalMinuteDecimals=Double.parseDouble(startIntervalMinuteDecimalsStr);//小数部分
						       
							   String startIntervalSecondIntStr="";
							   String startIntervalSecondDecimalsStr="";
							   String startIntervalMinuteDecimalsMultTempStr="";
							   String startIntervalSecondDecimalsMultStr="";
							   double startIntervalMinuteDecimalsMult=0.0; //小数乘法：整数部分为秒；小数部分为厘(省略)
							   startIntervalMinuteDecimalsMult=startIntervalMinuteDecimals*60;
							   startIntervalMinuteDecimalsMultTempStr=String.valueOf(startIntervalMinuteDecimalsMult);
							   startIntervalSecondDecimalsMultStr=df.format(startIntervalMinuteDecimalsMultTempStr);
							   String[] startIntervalSecondDecimalsArray=startIntervalSecondDecimalsMultStr.split(".");
							   if(startIntervalSecondDecimalsArray.length>0){ //小数点后省略,不计算
								   startIntervalSecondIntStr=startIntervalSecondDecimalsArray[0];
							   }
							   
							   if(startIntervalSecondIntStr!=null && !"".equals(startIntervalSecondIntStr)){ 
								   startIntervalSecond=Integer.parseInt(startIntervalSecondIntStr);
							   }
						   }
					   }
				   }
			   }
		   } 
	   }
	  
	   //更改相应字段标志(自己定义)
	   if(!"".equals(startTimeSecond)){//有默认值，则选默认值;其他选全部
		  startTimeSecondFlag=false;
	   }
	   if(!"".equals(startTimeMinute)){//有默认值，则选默认值;其他选全部
		  startTimeMinuteFlag=false;
	   }
	   if(!"".equals(startTimeHour)){//有默认值，则选默认值;其他选全部
		  startTimeHourFlag=false;
	   }
	   if(startIntervalDay!=0){  //有更改，则选更改值;其他选全部
		  startTimeDayFlag=false;
	   }
	   if(startIntervalMonth!=0){//有更改，则选更改值;其他选全部
		  startTimeMonthFlag=false;
	   }

	   cronExpression=startTimeSecondFlag?("* "):((startIntervalSecond==0)?(startTimeSecond+" "):("0/"+startIntervalSecond+" "));
	   cronExpression+=startTimeMinuteFlag?("* "):((startIntervalMinute==0)?(startTimeMinute+" "):("0/"+startIntervalMinute+" "));
	   cronExpression+=startTimeHourFlag?("* "):((startIntervalHour==0)?(startTimeHour+" "):("0/"+startIntervalHour+" "));
	   cronExpression+=startTimeDayFlag?("* "):((startIntervalDay==0)?(startTimeDay+" "):("1/"+startIntervalDay+" "));
	   cronExpression+=startTimeMonthFlag?("* "):((startIntervalMonth==0)?(startTimeMonth+" "):("1/"+startIntervalMonth+" ")); 
	   cronExpression+="?";

       return cronExpression;
    }
}