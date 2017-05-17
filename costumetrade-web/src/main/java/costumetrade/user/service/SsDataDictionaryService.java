package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SsDataDictionary;


public interface SsDataDictionaryService {

	/**
	 * 查询设置
	 * */
	public List<SsDataDictionary> getDataDictionarys(Integer storeId);
	
	/**
	 * 
	 * 新增设置内容
	 * */
	public List<SsDataDictionary> saveDataDictionary(SsDataDictionary dictionary);
	
	/**
	 * 删除设置
	 * */
	
	public List<SsDataDictionary> deleteDataDictionary(Integer id);
	
	/**
	 * 查询设置
	 * */
	public List<SsDataDictionary> getDataDicts(SsDataDictionary dictionary);
}
