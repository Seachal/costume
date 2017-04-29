package costumetrade.order.enums;

import org.apache.commons.lang3.StringUtils;

import costumetrade.common.Enum.EnumAutoTask;

public enum SeasonTypeEnum {

	SEASON_SPRING("spring","春"),
	SEASON_SUMMER("summer","夏"),
    SEASON_AUTUMN("autumn","秋"),
    SEASON_WINTER("winter","东");
    
    
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
}
