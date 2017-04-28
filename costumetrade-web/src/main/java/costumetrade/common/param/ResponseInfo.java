package costumetrade.common.param;

/**
 * <p>
 * Title: ResponseCodes.java<／p>
 * <p>
 * Description: <／p>
 * 
 * @author yh.yu
 * @date 2015年8月26日
 */
public enum ResponseInfo {

	SUCCESS(0, "success"), 
	EXCEPTION(-1, "系统异常"),
	
	NOT_DATA(1000, "查询无结果"), 
	INVALID_PARAM(1001,"非法参数"), 
	LOGIN_FAILURE(1002,"登录失败"), 
	LOGIN_EXPIRED(1003,"登录失效"), 
	AUTHENTICATION_FAILURE(1004,"认证失败"), 
	UNAUTHORIZED(1005, "无权限"), 
	BUSI_NOT_OPEN(1006,"业务未开通或已暂停"),
	PASSWORD_ERROR(1007,"密码错误"),
	MOBILE_VERIFICATION_CODE_ERROR(1008,"手机验证码错误"),
	LACK_PARAM(1009,"缺少参数"),
	TASK_EXPIRED(2000,"任务失效"), 
	;

	public final int code;

	public final String msg;

	private ResponseInfo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
