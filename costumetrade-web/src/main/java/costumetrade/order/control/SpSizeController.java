package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpPSizeKey;
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
	public ApiResponse getAllSizes(int corpId) {
		
		List<SpPSize> sizeLists = new ArrayList<SpPSize>();
		sizeLists = spPSizeService.getSpPSizes(corpId);

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
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteSize")
	@ResponseBody
	public ApiResponse deleteSize(int id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPSizeService.deleteSpPSize(id);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getAllSizeCustom")
	@ResponseBody
	public ApiResponse getAllSizeCustom(int corpId) {
		
		List<SpPSizeCustom> sizeCustomLists = new ArrayList<SpPSizeCustom>();
		sizeCustomLists = spPSizeCustomService.getSpPSizeCustoms(corpId);

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
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteSizeCustom")
	@ResponseBody
	public ApiResponse deleteSizeCustom(int id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPSizeCustomService.deleteSpPSizeCustom(id);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
}
