package costumetrade.pay.enums;

public enum EnumcheckNameType {

	
	NO_CHECK("NO_CHECK", "不校验真实姓名 "),
	FORCE_CHECK("FORCE_CHECK", "强校验真实姓名"),
	OPTION_CHECK("OPTION_CHECK", "针对已实名认证的用户才校验真实姓名 ");
    
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    EnumcheckNameType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
