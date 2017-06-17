package costumetrade.order.domain;



import costumetrade.common.Entity;

public class YDLogisticsRequest extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	private String orderId;
	
	private String mailno;
	private String khddh;
	
	private String nbckh;
	
	private String weight;
	
	private String size;
	
	private String value;
	
	private String freight;
	
	private String premium;
	
	private String otherCharges;
	
	private String orderType;
	
	private String waveNo;
	
	private String callbackId;
	
	private String collectionCurrency;
	
	private String collectionValue;
	
	private String special;
	
	private Item item;
	
	private Sender sender;
	
	private Sender receiver;
	
	private String remark;
	
	private String cusArea1;
	
	private String cusArea2;
	
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

	public String getKhddh() {
		return khddh;
	}

	public void setKhddh(String khddh) {
		this.khddh = khddh;
	}

	public String getNbckh() {
		return nbckh;
	}

	public void setNbckh(String nbckh) {
		this.nbckh = nbckh;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}

	public String getCollectionCurrency() {
		return collectionCurrency;
	}

	public void setCollectionCurrency(String collectionCurrency) {
		this.collectionCurrency = collectionCurrency;
	}

	public String getCollectionValue() {
		return collectionValue;
	}

	public void setCollectionValue(String collectionValue) {
		this.collectionValue = collectionValue;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Sender getReceiver() {
		return receiver;
	}

	public void setReceiver(Sender receiver) {
		this.receiver = receiver;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCusArea1() {
		return cusArea1;
	}

	public void setCusArea1(String cusArea1) {
		this.cusArea1 = cusArea1;
	}

	public String getCusArea2() {
		return cusArea2;
	}

	public void setCusArea2(String cusArea2) {
		this.cusArea2 = cusArea2;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getWaveNo() {
		return waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

	public String getCallbackId() {
		return callbackId;
	}

	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}
	
	
    
}