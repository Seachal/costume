package costumetrade.common.param;

import java.io.Serializable;

import costumetrade.common.page.Page;

/**
 * <p>
 * Title: ApiResponse.java<／p>
 * <p>
 * Description: <／p>
 * 
 * @author yh.yu
 * @date 2015年8月26日
 */
public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 1189991146334068265L;
	
	public static final ApiResponse EXCEPTION = new ApiResponse(ResponseInfo.EXCEPTION);

	public static final ApiResponse SUCCESS = new ApiResponse();
	
	public static final ApiResponse LOGIN_FAILURE = new ApiResponse(ResponseInfo.LOGIN_FAILURE);

	public static final ApiResponse LOGIN_EXPIRED = new ApiResponse(ResponseInfo.LOGIN_EXPIRED);

	public static final ApiResponse AUTHENTICATION_FAILURE = new ApiResponse(ResponseInfo.AUTHENTICATION_FAILURE);

	public static final ApiResponse TASK_EXPIRED = new ApiResponse(ResponseInfo.TASK_EXPIRED);
	
	public static final ApiResponse BUSI_NOT_OPEN = new ApiResponse(ResponseInfo.BUSI_NOT_OPEN);
	
	public static final ApiResponse INVALID_PARAM = new ApiResponse(ResponseInfo.INVALID_PARAM);

	public static final ApiResponse PASSWORD_ERROR = new ApiResponse(ResponseInfo.PASSWORD_ERROR);

	public static final ApiResponse MOBILE_VERIFICATION_CODE_ERROR = new ApiResponse(ResponseInfo.MOBILE_VERIFICATION_CODE_ERROR);
	
	public static final ApiResponse UNAUTHORIZED = new ApiResponse(ResponseInfo.UNAUTHORIZED);

	private Page page;

	private int code; // 返回响应码:0=成功返回，其他=错误返回

	private String msg; // 返回响应消息

	private Object data; // 返回数据

	private Object cfgData; // 配置数据
	
	public static ApiResponse getInstance( Object data) {
		ApiResponse response = new ApiResponse();
		return response.addData(data);
	}
	
	public static ApiResponse getInstance( Object data,Object cfgData) {
		ApiResponse response = new ApiResponse();
		return response.addData(data).addCfgData(cfgData);
	}

	public ApiResponse() {
		this(ResponseInfo.SUCCESS);
	}

	/**
	 * @param invalidParam
	 */
	public ApiResponse(ResponseInfo responseInfo) {
		this.code = responseInfo.code;
		this.msg = responseInfo.msg;
	}
	
	public ApiResponse(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public ApiResponse addData(Object data) {
		this.data = data;
		return this;
	}
	
	public ApiResponse addCfgData(Object cfgData) {
		this.cfgData = cfgData;
		return this;
	}
	
	public ApiResponse addPage(Page page) {
		this.setPage(page);
		return this;
	}

	/**
	 * @return the cfgData
	 */
	public Object getCfgData() {
		return cfgData;
	}

	/**
	 * @param cfgData
	 *            the cfgData to set
	 */
	public void setCfgData(Object cfgData) {
		this.cfgData = cfgData;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}
