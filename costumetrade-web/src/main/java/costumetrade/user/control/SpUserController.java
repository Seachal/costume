package costumetrade.user.control;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpStore;
import costumetrade.user.query.ScUserQuery;
import costumetrade.user.query.StoreQuery;
import costumetrade.user.service.SpEmployeeService;
import costumetrade.user.service.SpUserService;



/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/user")
@Controller
public class SpUserController {
	@Autowired
	private SpUserService spUserService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private SpEmployeeService spEmployeeService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ApiResponse login(String code,String appId,String appSecret) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(code == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		String openIdAndKey = weChatService.getOpenIdAndKey(code,appId,appSecret);
		JSONObject json = JSON.parseObject(openIdAndKey);
		String openid = json.getString("openid");
		ScWeChat chat = null;
		ScUserQuery resultQuery = new ScUserQuery();
		StoreQuery query = new StoreQuery();
		if(openid!=null){
			chat = spUserService.login(openid);
			query.setOpenid(openid);
			query = spUserService.getStores(query);
			resultQuery.setQuery(query);
			SpEmployee employee = spEmployeeService.getEmployeePrivilege(openid);
			SpEmployee e = new SpEmployee();
			if(employee!=null){
				e.setPrivilegeEmployees(employee.getPrivilegeEmployees());
				e.setZeroPrice(employee.getZeroPrice());
				e.setDiscount(employee.getDiscount());
				e.setModifyPrice(employee.getModifyPrice());
			}
			resultQuery.setEmployee(e);
			
		}
		if(chat == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(resultQuery);
		}
		return  result;
	}
	@RequestMapping("/saveUserOrStore")
	@ResponseBody
	public ApiResponse saveUserOrStore(SpStore store) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(store == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		Integer obj = spUserService.saveUserOrStore(store);
		return  ApiResponse.getInstance(obj);
	}

	
	
}
