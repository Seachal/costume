package costumetrade.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.service.SsDataDictionaryService;

@Transactional
@Service
public class SsDataDictionaryServiceImpl implements SsDataDictionaryService{
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;

	@Override
	public List<SsDataDictionary> getDataDictionarys(Integer storeId) {
		return ssDataDictionaryMapper.getDataDictionarys(storeId);
	}

	@Override
	public List<SsDataDictionary> saveDataDictionary(SsDataDictionary dictionary) {
		List<SsDataDictionary> dict = ssDataDictionaryMapper.select(dictionary);
		if(dict == null){
			int save = ssDataDictionaryMapper.insertSelective(dictionary);
		}
		return ssDataDictionaryMapper.getDataDictionarys(dictionary.getStoreId());
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
	

}
