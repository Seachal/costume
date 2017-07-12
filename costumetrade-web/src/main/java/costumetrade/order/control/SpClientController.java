package costumetrade.order.control;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.ScFocusShop;
import costumetrade.order.domain.SpClient;
import costumetrade.order.query.ClientQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpClientService;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.SsPayment;
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
	public ApiResponse saveClient(@RequestBody SpClient client) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		String save = spClientService.saveClient(client);
		if(save==null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else {
			result.setData(save);
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
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(client);
		}
		return result;
	}
	
	@RequestMapping("/getClient")
	@ResponseBody
	public ApiResponse getClient(String clientId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		SpClient client = spClientService.getClient(clientId);
		if(client == null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(client);
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
			result.setData(ResponseInfo.NOT_DATA.code);
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
		if(spClient == null ||StringUtil.isBlank(spClient.getStoreId())){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		Integer save = spClientService.updateClients(spClient);
		if(save == null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		return result;
	}
	@RequestMapping("/cancelFocus")
	@ResponseBody
	public ApiResponse cancelFocus(ScFocusShop focusShop) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(focusShop.getOpenid())
				|| focusShop.getShopid()==null
				){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int delete = spClientService.cancelFocus(focusShop);
		if(delete <= 0){
			result.setData(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/getTwoDimension")
	@ResponseBody
	public void getTwoDimension(HttpServletRequest request, HttpServletResponse response
			) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//spClientService.getTwoDimension1(null, resp, 200, 200);
		
		//二维码参数
        int width = 200; // 图像宽度  
        int height = 200; // 图像高度  
        String format = "png";// 图像类型  
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
        BitMatrix bitMatrix;
        try {
			bitMatrix = new MultiFormatWriter().encode("HTTPS://QR.ALIPAY.COM/FKX09481DX4VKLD3NVQYB3",BarcodeFormat.QR_CODE, width, height, hints);
			 MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	    
	     
		
	}
	@RequestMapping("/scanQRCode")
	@ResponseBody
	public ApiResponse scanQRCode(@RequestBody QRCodeScanParam param) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//System.out.println(param.getId()+","+param.getType()+","+param.getStoreId());
		JSONObject  scene = (JSONObject) JSONObject.toJSON(param);
		Object object =null;
		if(StringUtils.isNotBlank(scene.toJSONString())){
			object = weChatService.getTwoCode(scene.toJSONString());
		}else{
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		if(object == null){
			result.setData(ResponseInfo.EXCEPTION.code);
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
		if(object == null&&param.getType()==1){
			result.setData(ResponseInfo.SCAN_CUSTOMER_EXCEPTION.code);
			result.setMsg(ResponseInfo.SCAN_CUSTOMER_EXCEPTION.msg);
			return result;
		}else if(object == null&&param.getType()==2){
			result.setData(ResponseInfo.SCAN_SUPPLIER_EXCEPTION.code);
			result.setMsg(ResponseInfo.SCAN_SUPPLIER_EXCEPTION.msg);
			return result;
		}else if(object == null&&param.getType()==3){
			result.setData(ResponseInfo.SCAN_FRIEND_EXCEPTION.code);
			result.setMsg(ResponseInfo.SCAN_FRIEND_EXCEPTION.msg);
			return result;
		}else if(object == null&&param.getType()==4){
			result.setData(ResponseInfo.SCAN_EMP_EXCEPTION.code);
			result.setMsg(ResponseInfo.SCAN_EMP_EXCEPTION.msg);
			return result;
		}else {
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
			result.setData(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(object);
		}
		
		return result;
	}
	@RequestMapping("/financialCounting")
	@ResponseBody
	public ApiResponse financialCounting(OrderQuery query){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		List<OrderQuery> querys = spClientService.financialCounting(query);
		if(querys == null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(querys);
		}
		
		return result;
	}
	@RequestMapping("/saveAccountInfo")
	@ResponseBody
	public ApiResponse saveAccountInfo(SsPayment pay){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		Integer save = spClientService.saveAccountInfo(pay);
		if(save == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		
		return result;
	}
	@RequestMapping("/initAccountInfo")
	@ResponseBody
	public ApiResponse initAccountInfo(OrderQuery query){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;

		}
		SsPayment pay = spClientService.initAccountInfo(query);
		if(pay == null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(pay);
		}
		
		return result;
	}
	
	@RequestMapping("/clientReplenishment")
	@ResponseBody
	public ApiResponse clientReplenishment(OrderQuery query){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<ProductQuery> querys = spClientService.clientReplenishment(query);
		if(querys == null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(querys);
		}
		
		return result;
	}
	

	
}
