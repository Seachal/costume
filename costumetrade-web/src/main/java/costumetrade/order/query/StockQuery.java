package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.domain.SsStock;

public class StockQuery extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SsStock> stocks;
	
	private List<SsStoOrder> sellers;
	
	private String id;

	public List<SsStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<SsStock> stocks) {
		this.stocks = stocks;
	}

	public List<SsStoOrder> getSellers() {
		return sellers;
	}

	public void setSellers(List<SsStoOrder> sellers) {
		this.sellers = sellers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	
}
