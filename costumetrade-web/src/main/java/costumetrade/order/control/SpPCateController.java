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
import costumetrade.order.domain.SpPCate;
import costumetrade.order.service.SpPCateService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/cate")
@Controller
public class SpPCateController {
	@Autowired
	private SpPCateService spPCateService;

	@RequestMapping("/getAllCates")
	@ResponseBody
	public ApiResponse getAllCates(SpPCate cate) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(cate == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SpPCate> CateLists = new ArrayList<SpPCate>();
		CateLists = spPCateService.getSpPCates(cate);
		
		return  ApiResponse.getInstance(CateLists);
	}

	@RequestMapping("/saveCate")
	@ResponseBody
	public ApiResponse saveCate(SpPCate spPCate) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPCate == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPCateService.saveSpPCate(spPCate);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(save);
		}
		return result;

	}

	@RequestMapping("/deleteCate")
	@ResponseBody
	public ApiResponse deleteCate(@RequestParam List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPCateService.deleteSpPCate(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
}
