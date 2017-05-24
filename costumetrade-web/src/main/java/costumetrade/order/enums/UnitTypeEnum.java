package costumetrade.order.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum UnitTypeEnum {

	UNIT_ITEM("E","件"),
	UNIT_ITEMS("P","件批"),
    UNIT_A("A","个");
    
    
    private String code;
    private String name;
    
    UnitTypeEnum(String code, String name) {
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
     * 根据UNITType返回UNITTypeEnum对象
     * @param UNITType
     * @return
     */
   public static UnitTypeEnum getEnumUnitTypeByCode(String UnitType){
	   if(StringUtils.isBlank(UnitType)){
		   return null;
	   }
	   for (UnitTypeEnum UnitTypeEnum : values()) {
           if (StringUtils.equalsIgnoreCase(UnitTypeEnum.getKey(), UnitType))
               return UnitTypeEnum;
       }
	   return null;
   }
   

//   public static List<UnitTypeEnum> getUnitTypeEnum(){
//	   List<UnitTypeEnum> list = new ArrayList<UnitTypeEnum>();
//	   for (UnitTypeEnum unitTypeEnum : values()) {
//		   list.add(unitTypeEnum);
//	   }
//	   return list;
//   }
   public static List<String> getUnitTypeEnum(){
	   List<String> list = new ArrayList<String>();
	   for (UnitTypeEnum unitTypeEnum : values()) {
		   list.add(unitTypeEnum.getValue());
	   }
	   return list;
   }
}
