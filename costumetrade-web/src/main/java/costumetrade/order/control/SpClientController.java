package costumetrade.order.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpClient;
import costumetrade.order.query.ClientQuery;
import costumetrade.order.service.SpClientService;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.mapper.SpCustProdPriceMapper;
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
	private SsDataDictionaryService  ssDataDictionaryService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private SpCustProdPriceMapper custProdPriceMapper;

	@RequestMapping("/saveClient")
	@ResponseBody
	public ApiResponse saveClient(SpClient client) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		int save = spClientService.saveClient(client);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getClients")
	@ResponseBody
	public ApiResponse getClients(@RequestBody SpClient spClient) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<SpClient> client = spClientService.getClients(spClient);
		if(client.size()<=0){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
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
		
		SpClient client = spClientService.getClient(clientId);
		if(client == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		return result;
	}
	@RequestMapping("/initCustomer")
	@ResponseBody
	public ApiResponse initCustomer(ClientQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);

		ClientQuery data = spClientService.initCustomer(query);
		if(data == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(data);
		}

		return result;
	}
	@RequestMapping("/updateClients")
	@ResponseBody
	public ApiResponse updateClients(SpClient spClient) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spClient == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		Integer save = spClientService.updateClients(spClient);
		if(save == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		return result;
	}
/*	@RequestMapping("/deleteClient")
	@ResponseBody
	public ApiResponse deleteClient(SpClient client) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		Integer clientId = client.getId();
		int delete = spClientService.deleteClient(clientId);
		if(delete <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}*/
	@RequestMapping("/scanQRCode")
	@ResponseBody
	public ApiResponse scanQRCode(@RequestBody QRCodeScanParam param) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		JSONObject  scene = (JSONObject) JSONObject.toJSON(param);
		String object =null;
		if(StringUtils.isNotBlank(scene.toJSONString())){
			object = weChatService.getTwoCode(scene.toJSONString());
		}else{
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		if(object == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(object);
		}
		
		return result;
	}
	@RequestMapping("/scanQRCodeOk")
	@ResponseBody
	public ApiResponse scanQRCodeOk(@RequestBody QRCodeScanParam param) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		Object object = null;
		if(param !=null){
			object = spClientService.scanQRCodeOk(param);
		}else{
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		if(object == null){
			result.setCode(ResponseInfo.SUCCESS.code);
			result.setData(ResponseInfo.SCAN_EXCEPTION.code);
			result.setMsg(ResponseInfo.SCAN_EXCEPTION.msg);
			return result;
		}else{
			result.setData(object);
		}
		return result;
	}
	@RequestMapping("/getWechatTwoCode")
	@ResponseBody
	public ApiResponse getWechatTwoCode() throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		String object = weChatService.getWechatTwoCode();
		if(object == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(object);
		}
		
		return result;
	}

	
}
