package costumetrade.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.ScPrinterInfo;
import costumetrade.user.service.SpPrinterInfoService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/printer")
@Controller
public class SpPrinterController {
	@Autowired
	private SpPrinterInfoService spPrinterInfoService;
	
	@ResponseBody
	@RequestMapping(value ="/getPrinter", method=RequestMethod.POST)
	public ApiResponse getPrinters(String storeId) {
	
		return  ApiResponse.getInstance(spPrinterInfoService.getSpPrinterInfos(storeId));
	}

	@RequestMapping("/savePrinter")
	@ResponseBody
	public ApiResponse savePrinter(ScPrinterInfo scPrinterInfo) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(scPrinterInfo == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPrinterInfoService.saveSpPrinterInfo(scPrinterInfo);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(spPrinterInfoService.getSpPrinterInfos(scPrinterInfo.getStoreid()));
		}
		return result;

	}

	
}
