package costumetrade.user.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.user.domain.SpStore;

public class StoreQuery extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String openid;
	
	private List<SpStore> storeList;
	
	private Integer orderCount;
	
	private Integer messageCount;
	
	private Integer noticeCount;
	
	private String storeId;
	
	private List<ScPromotionalProduct> products;

	public List<SpStore> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<SpStore> storeList) {
		this.storeList = storeList;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

	public Integer getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(Integer noticeCount) {
		this.noticeCount = noticeCount;
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

	public List<ScPromotionalProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ScPromotionalProduct> products) {
		this.products = products;
	}
	
	
}