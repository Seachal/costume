package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpCart;
import costumetrade.order.service.SpCartService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/cart")
@Controller
public class SpCartController {
//	@Autowired
//	private SpCartService spCartService;
//
//
//	@RequestMapping("/getCarts")
//	@ResponseBody
//	public ApiResponse getAllSizes(int corpId) {
//		
//		List<SpCart> sizeLists = new ArrayList<SpCart>();
//		sizeLists = spCartService.getSpCarts(corpId);
//
//		return  ApiResponse.getInstance(sizeLists);
//	}
//
//	@RequestMapping("/saveCart")
//	@ResponseBody
//	public ApiResponse saveSize(SpCart spCart) {
//
//		ApiResponse result = new ApiResponse();
//		result.setCode(ResponseInfo.SUCCESS.code);
//		result.setMsg(ResponseInfo.SUCCESS.msg);
//		if(spCart == null ){
//			result.setCode(ResponseInfo.LACK_PARAM.code);
//			result.setMsg(ResponseInfo.LACK_PARAM.msg);
//			return result;
//		}
//		int save = spCartService.saveSpCart(spCart);
//		if(save<=0){
//			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
//			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
//			return result;
//		}
//		return result;
//
//	}
//
//	@RequestMapping("/deleteCart")
//	@ResponseBody
//	public ApiResponse deleteSize(int id) {
//
//		ApiResponse result = new ApiResponse();
//		result.setCode(ResponseInfo.SUCCESS.code);
//		result.setMsg(ResponseInfo.SUCCESS.msg);
//	
//		int delete = spCartService.deleteSpCart(id);
//		if(delete<=0){
//			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
//			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
//			return result;
//		}
//		return result;
//	}
//	

	
}
