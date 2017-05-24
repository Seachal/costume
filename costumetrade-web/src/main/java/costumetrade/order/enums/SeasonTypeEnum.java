package costumetrade.order.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum SeasonTypeEnum {

	SEASON_SPRING("spring","春"),
	SEASON_SUMMER("summer","夏"),
    SEASON_AUTUMN("autumn","秋"),
    SEASON_WINTER("winter","冬");
    
    
    private String code;
    private String name;
    
    SeasonTypeEnum(String code, String name) {
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
     * 根据seasonType返回SeasonTypeEnum对象
     * @param seasonType
     * @return
     */
   public static SeasonTypeEnum getEnumSeasonTypeByCode(String seasonType){
	   if(StringUtils.isBlank(seasonType)){
		   return null;
	   }
	   for (SeasonTypeEnum seasonTypeEnum : values()) {
           if (StringUtils.equalsIgnoreCase(seasonTypeEnum.getKey(), seasonType))
               return seasonTypeEnum;
       }
	   return null;
   }
   

   
//   public static List<SeasonTypeEnum> getSeasonTypeEnum(){
//	   List<SeasonTypeEnum> list = new ArrayList<SeasonTypeEnum>();
//	   for (SeasonTypeEnum seasonTypeEnum : values()) {
//		   list.add(seasonTypeEnum);
//	   }
//	   return list;
//   }
   public static List<String> getSeasonTypeEnum(){
	   List<String> list = new ArrayList<String>();
	   for (SeasonTypeEnum seasonTypeEnum : values()) {
		   list.add(seasonTypeEnum.getValue());
	   }
	   return list;
   }
}
