package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPColor;
import costumetrade.order.domain.SpPColorCustom;
import costumetrade.order.service.SpPColorCustomService;
import costumetrade.order.service.SpPColorService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/color")
@Controller
public class SpColorController {
	@Autowired
	private SpPColorService spPColorService;
	@Autowired
	private SpPColorCustomService spPColorCustomService;

	@RequestMapping("/getAllColors")
	@ResponseBody
	public ApiResponse getAllColors(String storeId) {
		ApiResponse result = new ApiResponse();
		if(storeId == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SpPColor> colorLists = new ArrayList<SpPColor>();
		colorLists = spPColorService.getSpPColors(Integer.valueOf(storeId));

		return  ApiResponse.getInstance(colorLists);
	}

	@RequestMapping("/saveColor")
	@ResponseBody
	public ApiResponse saveColor(SpPColor spPColor) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPColor == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPColorService.saveSpPColor(spPColor);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteColor")
	@ResponseBody
	public ApiResponse deleteColor(int id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		int delete = spPColorService.deleteSpPColor(id);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getAllColorCustoms")
	@ResponseBody
	public ApiResponse getAllColorCustoms( int corpId) {
		
		List<SpPColorCustom> colorCustomLists = new ArrayList<SpPColorCustom>();
		colorCustomLists = spPColorCustomService.getSpPColorCustoms(corpId);

		return  ApiResponse.getInstance(colorCustomLists);
	}

	@RequestMapping("/saveColorCustom")
	@ResponseBody
	public ApiResponse saveColorCustom( SpPColorCustom spPColorCustom) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPColorCustom == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPColorCustomService.saveSpPColorCustom(spPColorCustom);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteColorCustom")
	@ResponseBody
	public ApiResponse deleteColorCustom(int id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPColorCustomService.deleteSpPColorCustom(id);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
}
