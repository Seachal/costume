package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpCustomerType;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.query.DataDictionaryQuery;


public interface SsDataDictionaryService {

	/**
	 * 查询设置
	 * */
	public List<SsDataDictionary> getDataDictionarys(Integer storeId);
	
	/**
	 * 
	 * 新增设置内容
	 * */
	public Integer saveDataDictionary(List<SsDataDictionary> dictionarys);
	
	/**
	 * 删除设置
	 * */
	
	public List<SsDataDictionary> deleteDataDictionary(Integer id);
	
	/**
	 * 查询设置
	 * */
	public List<SsDataDictionary> getDataDicts(SsDataDictionary dictionary);
	
	/**
	 * 新增客户 修改客户类型初始化
	 * */
	
	public DataDictionaryQuery initCustomType(DataDictionaryQuery query);
	
	public SpCustomerType saveCustomType(SpCustomerType customerType);
	
	public int saveTypeOrGradeRate(SpCustProdPrice spCustProdPrice);
	
	public SpCustProdPrice getTypeOrGradeRate(SpCustProdPrice spCustProdPrice);
	
}
