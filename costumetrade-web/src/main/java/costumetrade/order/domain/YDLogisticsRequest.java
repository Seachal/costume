package costumetrade.order.domain;



import costumetrade.common.Entity;

public class YDLogisticsRequest extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status="";
	
	private String orderId="";
	
	private String mailno="";
	private String khddh="";
	
	private String nbckh="";
	
	private String weight="";
	
	private String size="";
	
	private String value="";
	
	private String freight="";
	
	private String premium="";
	
	private String otherCharges="";
	
	private String orderType="";
	
	private String waveNo="";
	
	private String callbackId="";
	
	private String collectionCurrency="";
	
	private String collectionValue="";
	
	private String special="";
	
	private Item item;
	
	private Sender sender;
	
	private Sender receiver;
	
	private String remark="";
	
	private String cusArea1="";
	
	private String cusArea2="";
	
	private String orderStatus="";
	
	private String msg="";
	
	private String printFile="";
	
	private String jsonData="";
	
	private String pdfInfo="";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status  == null ? "" : status.trim();
	}

	public String getOrderId() {
		return orderId == null ? "" : orderId.trim();
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId  == null ? "" : orderId.trim();
	}

	public String getMailno() {
		return mailno== null ? "" : mailno.trim();
	}

	public void setMailno(String mailno) {
		this.mailno = mailno == null ? "" : mailno.trim();
	}

	public String getOrderStatus() {
		return orderStatus == null ? "" : orderStatus.trim();
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus == null ? "" : orderStatus.trim();
	}

	public String getMsg() {
		return msg== null ? "" : msg.trim();
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? "" : msg.trim();
	}

	public String getPrintFile() {
		return printFile == null ? "" : printFile.trim();
	}

	public void setPrintFile(String printFile) {
		this.printFile = printFile == null ? "" : printFile.trim();
	}

	public String getJsonData() {
		return jsonData == null ? "" : jsonData.trim();
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData == null ? "" : jsonData.trim();
	}

	public String getPdfInfo() {
		return pdfInfo== null ? "" : pdfInfo.trim();
	}

	public void setPdfInfo(String pdfInfo) {
		this.pdfInfo = pdfInfo == null ? "" : pdfInfo.trim();
	}

	public String getKhddh() {
		return khddh== null ? "" : khddh.trim();
	}

	public void setKhddh(String khddh) {
		this.khddh = khddh == null ? "" : khddh.trim();
	}

	public String getNbckh() {
		return nbckh== null ? "" : nbckh.trim();
	}

	public void setNbckh(String nbckh) {
		this.nbckh = nbckh == null ? "" : nbckh.trim();
	}

	public String getWeight() {
		return weight== null ? "" : weight.trim();
	}

	public void setWeight(String weight) {
		this.weight = weight == null ? "" : weight.trim();
	}

	public String getSize() {
		return size== null ? "" : size.trim();
	}

	public void setSize(String size) {
		this.size = size == null ? "" : size.trim();
	}

	public String getValue() {
		return value== null ? "" : value.trim();
	}

	public void setValue(String value) {
		this.value = value == null ? "" : value.trim();
	}

	public String getFreight() {
		return freight== null ? "" : freight.trim();
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getPremium() {
		return premium== null ? "" : premium.trim();
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getOtherCharges() {
		return otherCharges== null ? "" : premium.trim();
	}

	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}

	public String getCollectionCurrency() {
		return collectionCurrency== null ? "" : premium.trim();
	}

	public void setCollectionCurrency(String collectionCurrency) {
		this.collectionCurrency = collectionCurrency;
	}

	public String getCollectionValue() {
		return collectionValue== null ? "" : premium.trim();
	}

	public void setCollectionValue(String collectionValue) {
		this.collectionValue = collectionValue;
	}

	public String getSpecial() {
		return special== null ? "" : premium.trim();
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
		return remark== null ? "" : premium.trim();
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCusArea1() {
		return cusArea1== null ? "" : premium.trim();
	}

	public void setCusArea1(String cusArea1) {
		this.cusArea1 = cusArea1;
	}

	public String getCusArea2() {
		return cusArea2== null ? "" : premium.trim();
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