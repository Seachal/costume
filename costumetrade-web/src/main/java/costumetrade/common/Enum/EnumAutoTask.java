
package costumetrade.common.Enum;

/**
 * 
 * @author luchunlong
 * @date 2017-03-13 
 * @function 定时触发任务枚举
 *
 */

public enum EnumAutoTask {
    
	AUTO_DOWN_FILE_TASK("autoDownFile","定时自动下载材料"),
	AUTOI_BANK_TASK("bankTask","银行任务"),
    AUTOI_COURT_TASK("courtTask","法院执行记录"),
    AUTOI_CARINVOICE_TASK("carInvoiceTask","购车发票"),
    AUTOI_CARCERTIFICATE_TASK("carCertificateTask","车辆登记证状态"),
    AUTOI_NETLOAN_TASK("netLoanTask","网贷记录"),
    AUTOI_POLICE_TASK("policeTask","公安信息"),
    AUTOI_MORTGAGE_TASK("mortgageTask","登记抵押");
    
    
    private String code;
    private String name;
    
    EnumAutoTask(String code, String name) {
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
     * 根据taskCode返回EnumAutoTask对象
     * @param taskCode
     * @return
     */
   public static EnumAutoTask getEnumAutoTaskByCode(String taskCode){
   	if(taskCode.equals(EnumAutoTask.AUTOI_COURT_TASK.getKey())){
   		return EnumAutoTask.AUTOI_COURT_TASK;
   	}else if(taskCode.equals(EnumAutoTask.AUTOI_CARINVOICE_TASK.getKey())){
   		return EnumAutoTask.AUTOI_CARINVOICE_TASK;
   	}if(taskCode.equals(EnumAutoTask.AUTOI_CARCERTIFICATE_TASK.getKey())){
   		return EnumAutoTask.AUTOI_CARCERTIFICATE_TASK;
   	}if(taskCode.equals(EnumAutoTask.AUTOI_NETLOAN_TASK.getKey())){
   		return EnumAutoTask.AUTOI_NETLOAN_TASK;
   	}if(taskCode.equals(EnumAutoTask.AUTOI_POLICE_TASK.getKey())){
   		return EnumAutoTask.AUTOI_POLICE_TASK;
   	}if(taskCode.equals(EnumAutoTask.AUTOI_MORTGAGE_TASK.getKey())){
   		return EnumAutoTask.AUTOI_MORTGAGE_TASK;
   	}
   	return EnumAutoTask.AUTOI_BANK_TASK;
   }
}
