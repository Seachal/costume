package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductParam;
import costumetrade.order.query.ProductQuery;


public interface SpProductService {
	/**
	 * 获取所有商品
	 * 
	 * */
	public List<ProductQuery> selectProducts(ProductParam productQuery);
	/**
	 * 获取商品詳情
	 * 
	 * */
	public ProductDetailQuery selectProduct(KeyParam keyParam);
	/**
	 * 获取商品詳情
	 * 
	 * */
	public int saveProduct(SpProduct product);
	/**
	 * 新增商品初始化查询
	 * 
	 * */
	public ProductInitQuery productInit(int corpId);

}
