package costumetrade.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum PayTypeEnum {

	PAY_CASH("1","现金支付"),
	PAY_ALIPAY("2","支付宝支付"),
	PAY_CARD("3","银行卡支付"),
	PAY_WEIXIN("4","微信支付");
    
    
    private String code;
    private String name;
    
    PayTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getKey() {
        return this.code;
    }
    
    public String getValue() {
        return this.name;
    }
    
    /**
     * 根据payType返回PayTypeEnum对象
     * @param payType
     * @return
     */
   public static PayTypeEnum getPayTypeByCode(String payType){
	   if(StringUtils.isBlank(payType)){
		   return null;
	   }
	   for (PayTypeEnum payTypeEnum : values()) {
           if (StringUtils.equalsIgnoreCase(payTypeEnum.getKey(), payType))
               return payTypeEnum;
       }
	   return null;
   }
   
   public static List<PayTypeEnum> getPayTypeEnum(){
	   List<PayTypeEnum> list = new ArrayList<PayTypeEnum>();
	   for (PayTypeEnum payTypeEnum : values()) {
		   list.add(payTypeEnum);
	   }
	   return list;
   }
}
