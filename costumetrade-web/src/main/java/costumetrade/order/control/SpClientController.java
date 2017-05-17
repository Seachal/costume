package costumetrade.order.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpClient;
import costumetrade.order.service.SpClientService;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.service.SsDataDictionaryService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/client")
@Controller
public class SpClientController {
	@Autowired
	private SpClientService spClientService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private SsDataDictionaryService  ssDataDictionaryService;
	
	@RequestMapping("/getTwoDimension")
	@ResponseBody
	public void getTwoDimension(HttpServletRequest req,HttpServletResponse resp) {
		
		spClientService.getTwoDimension1("http://www.baidu.com",resp, 200, 200);
		
	}

	@RequestMapping("/saveClient")
	@ResponseBody
	public ApiResponse saveClient(SpClient client) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//client.setId((Integer) httpSession.getAttribute("clientId"));
		
		int save = spClientService.saveClient(client);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getClients")
	@ResponseBody
	public ApiResponse getClients(Integer type) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		Integer clientId=(Integer) httpSession.getAttribute("clientId");
		
		List<SpClient> client = spClientService.getClients(clientId,type);
		if(client.size()<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getClient")
	@ResponseBody
	public ApiResponse getClient(Integer clientId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//Integer clientId=(Integer) httpSession.getAttribute("clientId");
		
		SpClient client = spClientService.getClient(clientId);
		if(client == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	@RequestMapping("/initCustomer")
	@ResponseBody
	public ApiResponse initCustomer(Integer storeId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//Integer clientId=(Integer) httpSession.getAttribute("clientId");
		SsDataDictionary dict = new SsDataDictionary();
		dict.setStoreId(storeId);
		dict.setDictGroup("CUSTOMER_TYPE");
		dict.setDictGroupName("客户类型");
		List<SsDataDictionary> data = ssDataDictionaryService.getDataDicts(dict);
		if(data == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(data);
		}
		return result;
	}
	@RequestMapping("/deleteClient")
	@ResponseBody
	public ApiResponse deleteClient(SpClient client) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//Integer clientId=(Integer) httpSession.getAttribute("clientId");
		Integer clientId = client.getId();
		int delete = spClientService.deleteClient(clientId);
		if(delete <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		//Integer type = Integer.parseInt(client.getType());
		return result;
	}

	
}
