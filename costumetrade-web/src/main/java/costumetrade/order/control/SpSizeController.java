package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.service.SpPSizeCustomService;
import costumetrade.order.service.SpPSizeService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/size")
@Controller
public class SpSizeController {
	@Autowired
	private SpPSizeService spPSizeService;
	@Autowired
	private SpPSizeCustomService spPSizeCustomService;

	@RequestMapping("/getAllSizes")
	@ResponseBody
	public ApiResponse getAllSizes(String storeId,String productId) {
		
		List<SpPSize> sizeLists = new ArrayList<SpPSize>();
		sizeLists = spPSizeService.getSpPSizes(storeId,productId);

		return  ApiResponse.getInstance(sizeLists);
	}

	@RequestMapping("/saveSize")
	@ResponseBody
	public ApiResponse saveSize(SpPSize spPSize) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPSize == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPSizeService.saveSpPSize(spPSize);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(save);
		}
		return result;

	}

	@RequestMapping("/deleteSize")
	@ResponseBody
	public ApiResponse deleteSize(@RequestParam List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPSizeService.deleteSpPSize(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getAllSizeCustom")
	@ResponseBody
	public ApiResponse getAllSizeCustom(String storeId) {
		
		List<SpPSizeCustom> sizeCustomLists = new ArrayList<SpPSizeCustom>();
		sizeCustomLists = spPSizeCustomService.getSpPSizeCustoms(storeId);

		return  ApiResponse.getInstance(sizeCustomLists);
	}

	@RequestMapping("/saveSizeCustom")
	@ResponseBody
	public ApiResponse saveSizeCustom(SpPSizeCustom spPSizeCustom) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPSizeCustom == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPSizeCustomService.saveSpPSizeCustom(spPSizeCustom);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(save);
		}
		return result;

	}

	@RequestMapping("/deleteSizeCustom")
	@ResponseBody
	public ApiResponse deleteSizeCustom(@RequestParam List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPSizeCustomService.deleteSpPSizeCustom(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
}
