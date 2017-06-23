package costumetrade.user.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.ScLogisticFee;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SsDataDictionary;

public class SettingQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SsDataDictionary>  datas;
	/**
	 * 支付二维码
	 * */
	private List<SsDataDictionary>  payQRCodes;
	/**
	 * 支付类型
	 * */
	private List<SsDataDictionary> payTypes;
	/**
	 * 费用类型
	 * */
	private List<SsDataDictionary>  feeTypes;
	/**
	 * 货品等级
	 * */
	private List<SsDataDictionary>  grades;
	/**
	 * 启用客户
	 * */
	private SsDataDictionary customerType;
	/**
	 * 销售价生成方式
	 * */
	private SsDataDictionary sellingMethod;
	/**
	 * 物流运费规则
	 * */
	private List<ScLogisticFee> logisticFees;
	/**
	 * 客户毛利
	 * */
	private List<SpCustProdPrice>  customerCusts;
	/**
	 * 积分换算
	 * */
	private SsDataDictionary pointsExchange;
	
	private List<SpStore> stores;
	
	private List<String> images;
	
	public List<SsDataDictionary> getPayQRCodes() {
		return payQRCodes;
	}
	public void setPayQRCodes(List<SsDataDictionary> payQRCodes) {
		this.payQRCodes = payQRCodes;
	}
	public List<SsDataDictionary> getPayTypes() {
		return payTypes;
	}
	public void setPayTypes(List<SsDataDictionary> payTypes) {
		this.payTypes = payTypes;
	}
	public List<SsDataDictionary> getFeeTypes() {
		return feeTypes;
	}
	public void setFeeTypes(List<SsDataDictionary> feeTypes) {
		this.feeTypes = feeTypes;
	}
	public List<SsDataDictionary> getGrades() {
		return grades;
	}
	public void setGrades(List<SsDataDictionary> grades) {
		this.grades = grades;
	}
	public SsDataDictionary getCustomerType() {
		return customerType;
	}
	public void setCustomerType(SsDataDictionary customerType) {
		this.customerType = customerType;
	}
	public List<ScLogisticFee> getLogisticFees() {
		return logisticFees;
	}
	public void setLogisticFees(List<ScLogisticFee> logisticFees) {
		this.logisticFees = logisticFees;
	}
	public List<SpCustProdPrice> getCustomerCusts() {
		return customerCusts;
	}
	public void setCustomerCusts(List<SpCustProdPrice> customerCusts) {
		this.customerCusts = customerCusts;
	}
	public SsDataDictionary getPointsExchange() {
		return pointsExchange;
	}
	public void setPointsExchange(SsDataDictionary pointsExchange) {
		this.pointsExchange = pointsExchange;
	}
	public SsDataDictionary getSellingMethod() {
		return sellingMethod;
	}
	public void setSellingMethod(SsDataDictionary sellingMethod) {
		this.sellingMethod = sellingMethod;
	}
	public List<SsDataDictionary> getDatas() {
		return datas;
	}
	public void setDatas(List<SsDataDictionary> datas) {
		this.datas = datas;
	}
	public List<SpStore> getStores() {
		return stores;
	}
	public void setStores(List<SpStore> stores) {
		this.stores = stores;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
   
	
}