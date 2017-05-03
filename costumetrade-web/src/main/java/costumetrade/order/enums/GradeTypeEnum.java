package costumetrade.order.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum GradeTypeEnum {

	GRADE_ONE("1","一级"),
	GRADE_TWO("2","二级"),
    GRADE_THREE("3","三级"),
    GRADE_FOUR("4","四级"),
	GRADE_FIVE("5","五级"),
	GRADE_SIX("6","六级");
    
    
    private String code;
    private String name;
    
    GradeTypeEnum(String code, String name) {
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
     * 根据GRADEType返回GRADETypeEnum对象
     * @param GRADEType
     * @return
     */
   public static GradeTypeEnum getEnumGradeTypeByCode(String gradeType){
	   if(StringUtils.isBlank(gradeType)){
		   return null;
	   }
	   for (GradeTypeEnum gradeTypeEnum : values()) {
           if (StringUtils.equalsIgnoreCase(gradeTypeEnum.getKey(), gradeType))
               return gradeTypeEnum;
       }
	   return null;
   }
   
   public static List<String> getGradeTypeEnum(){
	   List<String> list = new ArrayList<String>();
	   list.add(GradeTypeEnum.GRADE_FIVE.getValue());
	   list.add(GradeTypeEnum.GRADE_FOUR.getValue());
	   list.add(GradeTypeEnum.GRADE_ONE.getValue());
	   list.add(GradeTypeEnum.GRADE_SIX.getValue());
	   list.add(GradeTypeEnum.GRADE_THREE.getValue());
	   list.add(GradeTypeEnum.GRADE_TWO.getValue());
	   return list;
   }
}
