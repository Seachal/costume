package costumetrade.user.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.cache.Cache;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpPrivilegeEmployee;
import costumetrade.user.domain.SpStore;
import costumetrade.user.service.ISpPrivilegeService;
import costumetrade.user.service.SpStoreService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/store")
@Controller
public class SpStoreController {
	@Autowired
	private SpStoreService spStoreService;
	@Autowired
	private Cache cache;
	@Autowired
	private ISpPrivilegeService privilegeService;
	
	@RequestMapping("/getChainStore")
	@ResponseBody
	public ApiResponse getChainStore(String storeId) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(storeId == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		List<SpStore> storeLists = new ArrayList<SpStore>();
		storeLists = spStoreService.getChainStore(Integer.valueOf(storeId));

		return  ApiResponse.getInstance(storeLists);
	}
	
	@RequestMapping("/saveChainStore")
	@ResponseBody
	public ApiResponse saveChainStore(SpStore store) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(store == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		
		int save = spStoreService.saveChainStore(store);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.name());
			return result;
		}else if(save ==2){
			result.setCode(ResponseInfo.DATA_EXCEPTION.code);
			result.setMsg(ResponseInfo.DATA_EXCEPTION.name());
			return result;
		}
		
		return  result;
	}

	@RequestMapping("/deleteChainStore")
	@ResponseBody
	public ApiResponse deleteChainStore(Integer id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(id == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg("员工信息为空，不能保存");
			return result;
		}
		int save = spStoreService.deleteChainStore(id);
		
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;

	}
	@RequestMapping("/getStore")
	@ResponseBody
	public ApiResponse getStore(Integer storeId) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(storeId==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		SpStore store = spStoreService.getStore(storeId);
		
		if(store== null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(store);
		}
		return result;
		
	}


}
