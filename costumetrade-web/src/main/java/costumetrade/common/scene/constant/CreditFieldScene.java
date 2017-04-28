package costumetrade.common.scene.constant;

public enum CreditFieldScene {
	LOAN_CRDT("loanCrdt","贷款逾期记录",""),
	CARD_CRDT("cardCrdt","信用卡逾期记录",""),
	LEFT_NUM("leftNum","我行专项卡分期笔数","笔"),
	LEFT_AMOUNT("leftAmount","未结清余额","元"),
	NOTE("note","征信备注","");
	
	public final String code;
	
	public final String name;
	
	public final String unit;
	
	CreditFieldScene(String code, String name, String unit) {
        this.code = code;
        this.name = name;
        this.unit = unit;
    }

}
