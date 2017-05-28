package costumetrade.user.control;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
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
	
	@RequestMapping("/login")
	@ResponseBody
	public ApiResponse login(String code) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(code == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		String openIdAndKey = weChatService.getOpenIdAndKey(code);
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(openIdAndKey);
		String openid = json.getString("openid");
		ScWeChat chat = null;
		if(openid!=null){
			chat = spUserService.login(openid);
		}
		if(chat == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}

		return  ApiResponse.getInstance(chat);
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
