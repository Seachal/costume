package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpUnit;
import costumetrade.order.service.SpUnitService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/unit")
@Controller
public class SpUnitController {
	@Autowired
	private SpUnitService spUnitService;

	@RequestMapping("/getAllUnits")
	@ResponseBody
	public ApiResponse getAllUnits(@RequestBody SpUnit unit) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(unit == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SpUnit> UnitLists = new ArrayList<SpUnit>();
		UnitLists = spUnitService.getSpUnits(unit);

		return  ApiResponse.getInstance(UnitLists);
	}

	@RequestMapping("/saveUnit")
	@ResponseBody
	public ApiResponse saveUnit(SpUnit spUnit) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spUnit == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spUnitService.saveSpUnit(spUnit);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteUnit")
	@ResponseBody
	public ApiResponse deleteUnit(@RequestBody List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spUnitService.deleteSpUnit(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
}
