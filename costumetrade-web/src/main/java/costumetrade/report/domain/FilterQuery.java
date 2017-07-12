package costumetrade.report.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPColor;
import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.Filter;
import costumetrade.order.query.Rules;
import costumetrade.order.query.Sort;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SsPayment;

public class FilterQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<SpPSize> sizeList;
	
	private List<SpEmployee> employeeList;
	
	private List<SpClient> clientSuppliers;
	
	private List<SpClient> clientCustomers;
	
	private List<SpPColor> cplorList;
	
	private String openid;
	
	private String storeId;

	public List<SpPSize> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<SpPSize> sizeList) {
		this.sizeList = sizeList;
	}

	public List<SpEmployee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<SpEmployee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<SpClient> getClientSuppliers() {
		return clientSuppliers;
	}

	public void setClientSuppliers(List<SpClient> clientSuppliers) {
		this.clientSuppliers = clientSuppliers;
	}

	public List<SpClient> getClientCustomers() {
		return clientCustomers;
	}

	public void setClientCustomers(List<SpClient> clientCustomers) {
		this.clientCustomers = clientCustomers;
	}

	public List<SpPColor> getCplorList() {
		return cplorList;
	}

	public void setCplorList(List<SpPColor> cplorList) {
		this.cplorList = cplorList;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	
	
	
	
}