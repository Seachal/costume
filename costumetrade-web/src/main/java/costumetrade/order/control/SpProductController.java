package costumetrade.order.control;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.FTPClientUtils;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductParam;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/product")
@Controller
public class SpProductController {
	@Autowired
	private SpProductService spProductService;



	@RequestMapping("/getProducts")
	@ResponseBody
	public ApiResponse getAllroducts(ProductParam productQuery) {
		
		List<ProductQuery> productLists = new ArrayList<ProductQuery>();
	
		productLists = spProductService.selectProducts(productQuery);

		return  ApiResponse.getInstance(productLists);
	}
	
	@RequestMapping("/getProductDetail")
	@ResponseBody
	public ApiResponse getProduct(KeyParam keyParam) {
		
		
		ProductDetailQuery query = spProductService.selectProduct(keyParam);
		
		return  ApiResponse.getInstance(query);
	}
	
	@RequestMapping("/getProductInit")
	@ResponseBody
	public ApiResponse getProductInit(int corpId) {
		
		
		ProductInitQuery query = spProductService.productInit(corpId);
		
		return  ApiResponse.getInstance(query);
	}
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public ApiResponse saveProduct(SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		int save = spProductService.saveProduct(product);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return  result;
	}
	
	@RequestMapping("/uploadImage")
	@ResponseBody
	public ApiResponse uploadImage(@RequestParam("file")CommonsMultipartFile file) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//设置上传图片路径       upload/日期/
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String date = format.format(new Date());
		Integer d = Integer.valueOf(date);
		
		String fileName = file.getOriginalFilename();
		UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
		fileName =str+fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
		String path = "/touchart/"+d+"/";

		
		InputStream input;
		try {
			input = file.getInputStream();
			boolean upload = FTPClientUtils.getInstance().uploadFileToFtp(path, fileName, input);
			if(upload){
				result.setData(path+fileName);
			}else{
				result.setCode(ResponseInfo.EXCEPTION.code);
				result.setMsg(ResponseInfo.EXCEPTION.msg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return  result;
	}

	
	
}
