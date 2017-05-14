package costumetrade.pay.enums;


public enum EnumResultCode {

	

	RESULT_CODE_SUCCESS("SUCCESS", "微信反馈成功"),
	RESULT_CODE_FAIL("FAIL", "反馈失败");
    
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    EnumResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
