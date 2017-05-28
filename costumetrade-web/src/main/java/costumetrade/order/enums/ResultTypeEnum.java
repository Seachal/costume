package costumetrade.order.enums;

import java.util.ArrayList;
import java.util.List;

public enum ResultTypeEnum {

	RESULT_SUCCESS(1,"成功"),
	RESULT_FAIL(0,"失败"),
	RESULT_EXISTS(3,"已存在");
    
    
    private int code;
    private String name;
    
    ResultTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
     * 根据resultType返回resultTypeEnum对象
     * @param resultType
     * @return
     */
   public static ResultTypeEnum getEnumaResultTypeEnumCode(int resultType){

	   for (ResultTypeEnum resultTypeEnum : values()) {
           if (resultType == resultTypeEnum.getCode())
               return resultTypeEnum;
       }
	   return null;
   }
    
   public static List<Integer> getResultTypeEnum(){
	   List<Integer> list = new ArrayList<Integer>();
	   for (ResultTypeEnum resultTypeEnum : values()) {
		   list.add(resultTypeEnum.getCode());
	   }
	   return list;
   }
}
