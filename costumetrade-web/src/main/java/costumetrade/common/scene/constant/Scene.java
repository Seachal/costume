/*
 * Copyright (C) 2014-2016, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package costumetrade.common.scene.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 场景枚举类
 *
 * @author zhouyq
 * @since 2016年12月15日
 */
public enum Scene {

    CREDIT_MATERIALS_UPLOAD("creditMaterialsUpload", "征信材料上传", ""),
    
    CREDIT_MATERIALS_APPROVAL("creditMaterialsApproval", "征信材料审核", SceneCategory.APPROVAL),

    CREDIT_INPUT("creditInput", "征信结果录入", ""),

    CREDIT_APPROVAL("creditApproval", "征信预审核",SceneCategory.APPROVAL),

    CARD_INFO_INPUT("cardInfoInput", "开卡信息录入",""),

    CARD_INFO_APPROVAL("cardInfoApproval", "开卡审核",SceneCategory.APPROVAL),

    LOAN_INFO_INPUT("loanInfoInput", "贷款信息录入",""),

    USEDCAR_INFO_INPUT("usedCarInfoInput", "二手车信息录入",""),

    LOAN_MATERIALS_CHOOSE("loanMaterialsChoose", "贷款材料选择",""),

    BUSI_MODE_CHOOSE("busiModeChoose", "业务模式选择",""),

    HOME_MATERIALS_UPLOAD("homeMaterialsUpload", "上门材料上传",""),

    SIGN_MATERIALS_UPLOAD("signMaterialsUpload", "签约材料上传",""),

    LOAN_MATERIALS_UPLOAD("loanMaterialsUpload", "贷款材料上传",""),

    ADVANCE_MATERIALS_UPLOAD("advanceMaterialsUpload", "垫资材料上传",""),

    LOAN_TEL_APPROVAL("loanTelApproval", "电审",SceneCategory.APPROVAL),

    LOAN_APPROVAL("loanApproval", "贷款审核",SceneCategory.APPROVAL),

    MAKE_LOAN_APPROVAL("makeLoanApproval", "放款审核",SceneCategory.APPROVAL),

    PICK_MATERIALS_UPLOAD("pickMaterialsUpload", "提车材料上传",""),

    PICK_MATERIALS_APPROVAL("pickMaterialsApproval", "提车审核",SceneCategory.APPROVAL),
    
    REGISTRATION_MATERIALS_UPLOAD("registrationMaterialsUpload", "登记证材料上传",""),
    
    /**
     * 业务后台配置
     */
    ORDERDETAILS("OrderDetails", "订单详情", SceneCategory.BACKGROUNDCONFIG),
    APPLYMODIFY("ApplyModify", "申请修改贷款信息", SceneCategory.BACKGROUNDCONFIG),
    APPLYMODIFYAPPROVAL("ApplyModifyApproval", "贷款信息修改审核", SceneCategory.BACKGROUNDCONFIG),
    TERMINATIONORDERAPPROVA("TerminationOrderApproval", "终止订单审核", SceneCategory.BACKGROUNDCONFIG),
    

	/**
	 * 订单虚拟场景
	 */
    
    ORDER_INIT("orderInit", "订单初始化",""),
    
    ORDER_TERMINATE("orderTerminate", "订单取消",""),
    
	ORDER_COMPLETE("orderComplete", "业务完成",""),
	
	/**
	 * 服务虚拟场景
	 */
	STAGE("stage","银行分期服务",""),
	
	
	
	/**
	 * 贷后虚拟场景
	 */
	REGISTRATION("registration","办理上牌",""),
	
	REGISTRATION_APPROVAL("registrationApproval","上牌审核",""),
	
	
	PLEDGE("pledge","办理抵押",""),
	
	PLEDGE_APPROVAL("pledgeApproval","抵押审核","");
    
    public final String code;

    public final String name;
    
    public final String category;


    Scene(String code, String name, String category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }


    /**
     *  根据code查询名字
     *  @param code 场景编号
     *  @return 场景名字
     */
    public static String getName(String code) {
        Scene[] scenes = Scene.values();
        for (Scene scene : scenes) {
            if (scene.code.equals(code)) {
                return scene.name;
            }
        }
        return StringUtils.EMPTY;
    }

	public static Scene get(String code) {
		 Scene[] scenes = Scene.values();
	        for (Scene scene : scenes) {
	            if (scene.code.equals(code)) {
	                return scene;
	            }
	        }
		return null;
	}


	/**
	 *  @param sceneCode
	 */
	public static boolean isApproval(String sceneCode) {
		Scene scene = get(sceneCode);
		if(scene==null){
			return false;
		}
		return SceneCategory.APPROVAL.equals(scene.category);
		
	}
	
	/**
	 *  @param sceneCode
	 */
	public static boolean isBackgroundConfig(String sceneCode) {
		Scene scene = get(sceneCode);
		if(scene==null){
			return false;
		}
		return SceneCategory.BACKGROUNDCONFIG.equals(scene.category);
		
	}
}
