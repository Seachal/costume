package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.user.domain.SpCustProdPrice;

public class ClientQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SpClient> clientList;
	
	private List<SpCustProdPrice> custProdPriceList;
	    
	
	private List<String> districtList;
	private Integer type;
	
	private Integer storeId;
	
	

	public List<SpClient> getClientList() {
		return clientList;
	}

	public void setClientLsit(List<SpClient> clientList) {
		this.clientList = clientList;
	}

	public List<SpCustProdPrice> getCustProdPriceList() {
		return custProdPriceList;
	}

	public void setCustProdPriceList(List<SpCustProdPrice> custProdPriceList) {
		this.custProdPriceList = custProdPriceList;
	}

	public List<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<String> districtList) {
		this.districtList = districtList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	
	
}