package costumetrade.order.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpClient;
import costumetrade.order.service.SpClientService;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SsDataDictionary;
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
	
	
	@RequestMapping("/getTwoDimension")
	@ResponseBody
	public void getTwoDimension(HttpServletRequest req,HttpServletResponse resp) {
		
		//spClientService.getTwoDimension1("http://www.baidu.com",resp, 200, 200);
		 String url="www.baidu.com";
		         if(url!=null&&!"".equals(url)){
		             ServletOutputStream stream=null;
		             try {
		                 int width=200;
		                int height=200;
		                 stream=resp.getOutputStream();
		                QRCodeWriter writer=new QRCodeWriter();
		                BitMatrix m=writer.encode(url, BarcodeFormat.QR_CODE, height,width);
		                MatrixToImageWriter.writeToStream(m, "png", stream);
		             } catch (Exception e) {
		                 // TODO: handle exception
		                 e.printStackTrace();
		             }finally{
		                if(stream!=null){
		                    try {
		                        stream.flush();
		                        stream.close();
		                     } catch (IOException e) {
		                         // TODO Auto-generated catch block
		                        e.printStackTrace();
		                    }
		                    
		                 }
		           }
		}

		
	}
	

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
	public ApiResponse getClients(SpClient spClient) {
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
	public ApiResponse initCustomer(Integer storeId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		SpCustProdPrice tempProdPrice = new SpCustProdPrice();
		tempProdPrice.setStoreid(storeId);
		
		List<SpCustProdPrice> custProdList = custProdPriceMapper.select(tempProdPrice);
	    result.setData(custProdList);
		
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
	public ApiResponse scanQRCode(String sceneStr) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		String object = weChatService.getTwoCode(sceneStr);
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
