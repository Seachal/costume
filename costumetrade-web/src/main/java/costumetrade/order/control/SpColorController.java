package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPColor;
import costumetrade.order.domain.SpPColorKey;
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

	@RequestMapping("/getAllColors")
	@ResponseBody
	public ApiResponse getAllColors(String corpId) {
		
		List<SpPColor> colorLists = new ArrayList<SpPColor>();
		colorLists = spPColorService.getSpPColors(Integer.valueOf(corpId));

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
	public ApiResponse deleteColor(@RequestParam SpPColorKey spPColorKey) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(spPColorKey == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int delete = spPColorService.deleteSpPColor(spPColorKey);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
}
