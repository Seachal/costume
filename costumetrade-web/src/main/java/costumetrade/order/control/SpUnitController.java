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
	public ApiResponse getAllUnits(SpUnit unit) {
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
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(save);
		}
		return result;

	}

	@RequestMapping("/deleteUnit")
	@ResponseBody
	public ApiResponse deleteUnit(@RequestParam List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spUnitService.deleteSpUnit(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
}
