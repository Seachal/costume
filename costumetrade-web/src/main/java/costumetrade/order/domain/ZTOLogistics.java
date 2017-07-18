package costumetrade.order.domain;



import java.util.List;

import costumetrade.common.Entity;

public class ZTOLogistics extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private String tradeId;
	
	private String mailNo;
	private String partnerCode;
	
	private String siteName;
	
	private String starttime;
	
	private String endtime;
	
	private String weight;
	
	private String size;
	
	private String quantity;
	
	private String price;
	
	private String freight;
	
	private String premium;
	
	private String packCharges;
	
	private String otherCharges;
	
	private String orderSum;
	
	private String collectMoneytype;
	
	private Item collectSum;
	
	private Sender sender;
	
	private Sender receiver;
	
	private Item item;
	
	private String remark;
	
	private Boolean reslut;
	
	private String reason;
	
	private String orderCode;
	
	private String billCode;
	
	private List<Trace> traces;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getPackCharges() {
		return packCharges;
	}

	public void setPackCharges(String packCharges) {
		this.packCharges = packCharges;
	}

	public String getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}

	public String getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}

	public String getCollectMoneytype() {
		return collectMoneytype;
	}

	public void setCollectMoneytype(String collectMoneytype) {
		this.collectMoneytype = collectMoneytype;
	}

	public Item getCollectSum() {
		return collectSum;
	}

	public void setCollectSum(Item collectSum) {
		this.collectSum = collectSum;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Boolean getReslut() {
		return reslut;
	}

	public void setReslut(Boolean reslut) {
		this.reslut = reslut;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public List<Trace> getTraces() {
		return traces;
	}

	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}
	
	

	
    
}