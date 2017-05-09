package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.ProductQuery;


public interface SpProductService {
	/**
	 * 获取所有商品
	 * 
	 * */
	public List<SpProduct> selectProducts(ProductQuery productQuery);
	/**
	 * 获取商品詳情
	 * 
	 * */
	public SpProduct selectProduct(ProductQuery productParam);
	/**
	 * 获取商品詳情
	 * 
	 * */
	public int saveProduct(SpProduct product);
	/**
	 * 新增商品初始化查询
	 * 
	 * */
	public ProductQuery productInit(int corpId);

}
