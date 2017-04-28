package costumetrade.common.util;

/**
 * 批量导入逾期记录时
 * excel中每行对应的字段
 * @author wl
 *
 */
public class ExcelColumns {
	
	public static enum columns{
		idCard("idCard",0),
		userName("userName",1),
		cardNumber("cardNumber",2),
		stageMoney("stageMoney",3),
		statementDate("statementDate",4),
		cardBalance("cardBalance",5),
		overdueAmount("overdueAmount",6),
		companyAdvance("companyAdvance",7),
		currentDefaultNum("currentDefaultNum",8),
		cumulativeDefaultNum("cumulativeDefaultNum",9);
		
		private String name;
		private int index;
		columns(String name,int index){
			this.name = name;
		}
	}
	
	public static String getName(int index){
		return columns.values()[index].name;
	}
	
	public static int getIndex(String name){
		return columns.valueOf(name).index;
	}
	
}
