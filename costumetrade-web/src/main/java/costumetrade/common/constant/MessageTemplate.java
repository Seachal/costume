/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :wl
 * Create Date:2016年6月21日
 */
package costumetrade.common.constant;

/**
 * ToDo       :短信模板
 * @author wl
 * @version MessageTemplate.java,2016年6月21日 下午1:48:26
 */
public class MessageTemplate {
	
	/**订单号*/
	public static final String orderNo = "orderNo";
	
	/**申请人姓名*/
	public static final String applicantName = "applicantName";
	
	/**车商名称*/
	public static final String carShopName = "carShopName";
	
	/**还款金额*/
	public static final String repaymentMoney = "repaymentMoney";
	
	/**账号*/
	public static final String account = "account";
	
	/**帐户名*/
	public static final String accountName = "accountName";
	
	/**借款金额*/
	public static final String loanMoney = "loanMoney";
	
	/**发送银行时间*/
	public static final String sendBankTime = "sendBankTime";
	
	/**机构名称*/
	public static final String deptName = "deptName";
	
	/** 审核意见 */
	public static final String auditOpinion = "auditOpinion";
	
	/** 业务员姓名 */
	public static final String salesmanName = "salesmanName";
	
	/** 提车时间 */
	public static final String takeCarTime = "takeCarTime";

	/********************************************************************************/
	
	/** 平台征信材料退回  车商业务员收到 */
	public static String creditBackService = "【惠融金服】"+applicantName+"的征信材料因不符合要求被退回，请及时处理。";

	/** 订单由客服提交到协调岗 */
	public static String distributeOrder = "【惠融金服】您有一个新的汽车分期申请订单需要派发，请尽快处理。";
	
	/** 协调岗订单分发或客服审核征信材料后直接到   客户经理收到 */
	public static String submitToCredit = "【惠融金服】您有一个客户为"+applicantName+"的分期申请需要受理，请您及时处理。";
	
	/** 增加共同还款人  车商业务员收到 */
	public static String orderBackisNeedCommonPer = "【惠融金服】"+applicantName+"的订单被退回，银行要求增加共同还款人，原因："+auditOpinion+"，请联系客户上传共同还款人征信材料，如有疑问请联系客户经理"+account+"。";
	
	/** 客户经理选择了不上门调查，征信审核通过  */
	public static String creditAuditPass = "【惠融金服】银行已正式受理"+applicantName+"的分期申请，请引导客户关注“惠融金服”微信公众平台并上传分期材料。";
	
	/** 客户经理选择了要上门调查，征信审核通过 */
	public static String creditAuditPassVisit = "【惠融金服】"+applicantName+"的征信材料已审核通过，请告知客户等待银行上门调查。";

	/** 贷款材料审核通过  审核岗收到 */
	public static String serviceSubmitLoanOrder = "【惠融金服】"+applicantName+"的材料已提交完成，请及时审核。";
	
	/** 客服贷款材料审核退回   客户经理收到*/
	public static String orderBackService = "【惠融金服】"+applicantName+"的材料不符合要求已退回，请及时处理。";
	
	/** 审核岗退回订单不增加上门调查  客户经理收到*/
	public static String orderBackAudit = "【惠融金服】"+applicantName+"的分期申请经审核，已被退回。原因："+auditOpinion+"。";
	
	/** 审核岗退回订单需要增加上门调查  客户经理收到*/
	public static String orderBackAuditVisit = "【惠融金服】"+applicantName+"的分期申请经审核，已被退回。原因：增加上门调查，请及时处理。";
	
	/** 被拒单 */
	public static String refuseOrderManager = "【惠融金服】"+applicantName+"的分期申请经审核，已被拒绝。原因："+auditOpinion+"。";

	/** 贷款审核终审通过 客户经理收到  */
	public static String determineCrInformation = "【惠融金服】"+applicantName+"的分期申请经审核，已通过。请及时联系车商和客户，安排提车。";
	
	/** 客户经理提交分期信息修改申请，审核岗收到 */
	public static String stageInformationAudit = "【惠融金服】客户经理修改了"+applicantName+"的购车分期方案，请及时确认。";
	
	/** 分期信息修改申请通过，客户经理收到 */
	public static String stageInformationPass = "【惠融金服】"+applicantName+"的分期申请修改已通过，新的分期信息已更新，请查看。";
	
	/** 分期信息修改申请被拒绝 */
	public static String stageInformationBack = "【惠融金服】"+applicantName+"的分期申请修改已被拒绝。";
	
	/** 客服审核签约材料退回 */
	public static String signBackService = "【惠融金服】"+applicantName+"的签约材料经审核，已被退回。";
	
	/** 签约材料审核通过 */
	public static String signMaterialsPass = "【惠融金服】"+applicantName+"的签约材料审核通过，平台会尽快放款，请耐心等待。放款后可以将购车发票和注册登记证上传至平台检验真假。";
	
	/** 订单提交到征信查询岗 */
	public static String creditInquiry = "【惠融金服】您有一个客户为"+applicantName+"的分期申请需要征信查询，请您及时处理。";
	
	/** 客服通知客户经理材料上传完成 */
	public static String cusServiceCall = "【惠融金服】"+applicantName+"的申贷材料客户已经上传，平台客服已经分类完成，请查看贷款材料进行处理。";
	
	/** 贷款审核通过，车商收到 */
	public static String loanMaterialsPass = "【惠融金服】"+applicantName+"的分期申请经审核，已通过。请及时联系客户和客户经理，并通过APP提供提车时间。";
	
	/** 提车时间成功预约后  */
	public static String makeLifeTimeMsg = "【惠融金服】"+carShopName+"的"+salesmanName+"已为购车人"+applicantName+"预约了"+takeCarTime+"提车。";
	
	/** 提车材料被退回 */
	public static String carMaterialsBack = "【惠融金服】"+applicantName+"的提车材料，因材料不符合要求，已被退回。";

	/** 提车材料审核通过 */
	public static String carMaterialsPass = "【惠融金服】"+applicantName+"的提车材料平台已经查验，请查看验证结果。该笔订单办理注册登记证后也可上传平台进行查验";
	
	/** 注册登记证被退回 */
	public static String carRegisterMaterialsBack = "【惠融金服】"+applicantName+"的注册登记证，因材料不符合要求，已被退回。";

	/** 注册登记证审核通过 */
	public static String carRegisterMaterialsPass = "【惠融金服】"+applicantName+"的注册登记证平台已经查验，请查看验证结果。";
	
	/** 业务模式确定为担保模式  */
	public static String INFORM_GUARRANTEE_CORP = "【惠融金服】%s的分期申请已被银行确定为担保模式，请拨打%s联系客户。";
	
}
