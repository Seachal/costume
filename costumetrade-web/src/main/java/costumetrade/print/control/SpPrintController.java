package costumetrade.print.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.StringUtil;
import costumetrade.print.service.SpPrintService;
import costumetrade.user.domain.ScPrinterInfo;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/print")
@Controller
@ResponseBody
public class SpPrintController {
	@Autowired
	private SpPrintService spPrintService;
	
	@RequestMapping("/gbk")
	public ApiResponse gbk(@RequestParam(value = "image", required = false)CommonsMultipartFile image,@RequestParam(value = "text", required = false)String text){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		String data = null;
		if(StringUtil.isNotBlank(text)){
			data = spPrintService.gbkText(text);
		}else{
			data = spPrintService.gbkImage(image);
		}
		if(StringUtil.isBlank(data)){
			result.setData(ResponseInfo.CHANGE_EXCEPTION.code);
			result.setMsg(ResponseInfo.CHANGE_EXCEPTION.msg);
		}else{
			result.setData(data);
		}
		return result;
	}
	@RequestMapping("/getPrintUserId")
	public ApiResponse getPrintUserId(ScPrinterInfo info){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		String data = null;
		if(StringUtil.isBlank(info.getPrintid())||StringUtil.isBlank(info.getStoreid())){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		data = spPrintService.getUserIdPrinter(info);
		if(StringUtil.isBlank(data)){
			result.setData(ResponseInfo.CHANGE_EXCEPTION.code);
			result.setMsg(ResponseInfo.CHANGE_EXCEPTION.msg);
		}else{
			result.setData(data);
		}
		return result;
	}
}
