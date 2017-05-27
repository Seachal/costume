package costumetrade.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import costumetrade.user.domain.PriceJson;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpCustomerType;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpCustomerTypeMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.query.DataDictionaryQuery;
import costumetrade.user.service.SsDataDictionaryService;

@Transactional
@Service
public class SsDataDictionaryServiceImpl implements SsDataDictionaryService{
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SpCustomerTypeMapper spCustomerTypeMapper;
	@Autowired
	private SpCustProdPriceMapper  spCustProdPriceMapper;
	
	@Override
	public List<SsDataDictionary> getDataDictionarys(Integer storeId) {
		return ssDataDictionaryMapper.getDataDictionarys(storeId);
	}

	@Override
	public List<SsDataDictionary> saveDataDictionary(List<SsDataDictionary> dictionarys) {
		for(SsDataDictionary dictionary : dictionarys){
			List<SsDataDictionary> dict = ssDataDictionaryMapper.select(dictionary);
			if(dict == null){
				dictionary.setStatus(0);
				ssDataDictionaryMapper.insertSelective(dictionary);
			}else{
				dictionary.setId(dict.get(0).getId());
				ssDataDictionaryMapper.updateByPrimaryKeySelective(dictionary);
			}
			if("CUSTOMER_TYPE".equals(dictionary.getDictGroup())){
				SpCustomerType type = new SpCustomerType();
				type.setTypename(dictionary.getDictValue());
				type.setStoreid(dictionary.getStoreId());
				type.setSaletype(dictionary.getSaletype());
				type.setCreateBy(dictionary.getCreateBy());
				type.setCreateTime(dictionary.getCreateTime());
				type.setIntegration(dictionary.getIntegration());
				type.setVisibleGrade(dictionary.getVisibleGrade());
				type.setModifyBy(dictionary.getCreateBy());
				type.setModifyTime(new Date());
				SpCustomerType customType = spCustomerTypeMapper.selectByName(type);
				if(customType == null ){
					spCustomerTypeMapper.insertSelective(type);
				}else{
					type.setId(customType.getId());
					spCustomerTypeMapper.updateByPrimaryKeySelective(type);
				}
			}
		}
		Integer storeId = dictionarys.get(0).getStoreId();
		return ssDataDictionaryMapper.getDataDictionarys(storeId);
	}

	@Override
	public List<SsDataDictionary> deleteDataDictionary(Integer id) {
		SsDataDictionary dict = new SsDataDictionary();
		dict.setId(id);
		dict.setStatus(1);
		int delete = ssDataDictionaryMapper.updateByPrimaryKeySelective(dict);
		dict = ssDataDictionaryMapper.selectByPrimaryKey(id);
		return ssDataDictionaryMapper.getDataDictionarys(dict.getStoreId());
	}

	@Override
	public List<SsDataDictionary> getDataDicts(SsDataDictionary dictionary) {
		return ssDataDictionaryMapper.select(dictionary);
	}

	@Override
	public DataDictionaryQuery initCustomType(DataDictionaryQuery query) {
		List<String> list = new ArrayList<String>();
		list.add("SALE_PRICE");//价格名称
		list.add("PRODUCT_LEVEL");//货品级别
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,query.getStoreId());
		List<String> gradeList = new ArrayList<String>();
		List<String> priceNameList = new ArrayList<String>();
		if(dict !=null && dict.size()>0){
			for(SsDataDictionary d : dict){
				if("SALE_PRICE".equals(d.getDictGroup())){
					priceNameList.add(d.getDictValue());
				}else if("PRODUCT_LEVEL".equals(d.getDictGroup())){
					gradeList.add(d.getDictValue());
				}
			}
		}
		DataDictionaryQuery queryResult = new DataDictionaryQuery();
		queryResult.setGradeList(gradeList);
		queryResult.setPriceNameList(priceNameList);
		//编辑修改页面：判断customtype 是否有保存过，有就查，没有就只返回初始值
		if(queryResult.getTypename() != null){
			SpCustomerType record = new SpCustomerType();
			record.setTypename(query.getTypename());
			record.setStoreid(query.getStoreId());
			record = spCustomerTypeMapper.selectByName(record);
			if(record != null){
				queryResult.setIntegration(record.getIntegration());
				queryResult.setId(record.getId());
				queryResult.setVisibleGrade(record.getVisibleGrade());
				queryResult.setSaletype(record.getSaletype());
			}
		}
		queryResult.setStoreId(query.getStoreId());
		queryResult.setTypename(query.getTypename());
		return queryResult;
	}

	@Override
	public SpCustomerType saveCustomType(SpCustomerType customerType) {
		SpCustomerType type = spCustomerTypeMapper.selectByName(customerType);
		if(type == null){
			spCustomerTypeMapper.insertSelective(customerType);
		}else{
			customerType.setId(type.getId());
			spCustomerTypeMapper.updateByPrimaryKeySelective(customerType);
		}
		return customerType;
	}

	@Override
	public int saveTypeOrGradeRate(SpCustProdPrice spCustProdPrice) {
		spCustProdPrice.setCustpricejson(JSONArray.toJSONString(spCustProdPrice.getCustPriceJson()));
		spCustProdPrice.setDiscpricejson(JSONArray.toJSONString(spCustProdPrice.getDiscPriceJson()));
		return spCustProdPriceMapper.insertSelective(spCustProdPrice);
	}

	@Override
	public SpCustProdPrice getTypeOrGradeRate(SpCustProdPrice spCustProdPrice) {
		SpCustProdPrice custProdPrice = spCustProdPriceMapper.selectByPrimaryKey(spCustProdPrice.getId());
		custProdPrice.setCustPriceJson((List<PriceJson>) JSONArray.parse(custProdPrice.getCustpricejson()));
		custProdPrice.setDiscPriceJson((List<PriceJson>) JSONArray.parse(custProdPrice.getDiscpricejson()));
		custProdPrice.setCustpricejson(null);
		custProdPrice.setDiscpricejson(null);
		return custProdPrice;
	}
	

}
