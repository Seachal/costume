package costumetrade.order.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPrinterInfo;
import costumetrade.order.service.SpPrinterInfoService;

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
	public ApiResponse getPrinters(int id) {
		
		SpPrinterInfo printer = new SpPrinterInfo();
		printer = spPrinterInfoService.getSpPrinterInfos(id);
		System.out.println(printer.toString());
		return  ApiResponse.getInstance(printer);
	}

	@RequestMapping("/savePrinter")
	@ResponseBody
	public ApiResponse savePrinter(SpPrinterInfo spPrinterInfo) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPrinterInfo == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPrinterInfoService.saveSpPrinterInfo(spPrinterInfo);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deletePrinter")
	@ResponseBody
	public ApiResponse deletePrinter(int id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPrinterInfoService.deleteSpPrinterInfo(id);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
}
