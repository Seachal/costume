package costumetrade.order.domain;

import java.util.Date;

import costumetrade.common.Entity;

public class YDLogisticsResponse extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	private String orderId;
	
	private String mailno;
	
	private String orderStatus;
	
	private String msg;
	
	private String printFile;
	
	private String jsonData;
	
	private String pdfInfo;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPrintFile() {
		return printFile;
	}

	public void setPrintFile(String printFile) {
		this.printFile = printFile;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getPdfInfo() {
		return pdfInfo;
	}

	public void setPdfInfo(String pdfInfo) {
		this.pdfInfo = pdfInfo;
	}
	
	
    
}