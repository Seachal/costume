package costumetrade.order.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.FTPClientUtils;
import costumetrade.common.word.ImageUtils;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsProductFile;
import costumetrade.order.domain.SsStock;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.service.SsDataDictionaryService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/product")
@Controller
public class SpProductController {
    public static Logger logger = Logger.getLogger(SpProductController.class);
    @Autowired
    private SsDataDictionaryService ssDataDictionaryService;
    @Autowired
    private HttpSession httpSession;
	@Autowired
	private SpProductService spProductService;

	@RequestMapping("/getProducts")
	@ResponseBody
	public ApiResponse getAllroducts(ProductQuery paramProduct) {
		paramProduct.setClientId((Integer) httpSession.getAttribute("clientId"));
		if(paramProduct.getStatus() == null){
			paramProduct.setStatus(0);
		}
		List<SpProduct> productLists = spProductService.selectProducts(paramProduct);
		return  ApiResponse.getInstance(productLists);
	}
	
	@RequestMapping("/getProductDetail")
	@ResponseBody
	public ApiResponse getProduct(ProductQuery paramProduct) {
		paramProduct.setClientId((Integer) httpSession.getAttribute("clientId"));
		ProductQuery product = spProductService.selectProduct(paramProduct);
		return  ApiResponse.getInstance(product);
	}
	
	@RequestMapping("/getProductInit")
	@ResponseBody
	public ApiResponse getProductInit(String storeId ) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(StringUtils.isBlank(storeId)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		ProductQuery query = spProductService.productInit(Integer.valueOf(storeId));
		
		
		return  ApiResponse.getInstance(query);
	}
	@RequestMapping("/updateProducts")
	@ResponseBody
	public ApiResponse updateProducts(@RequestParam("storeId")String storeId ,@RequestParam("idArray")List<String> idArray) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(StringUtils.isBlank(storeId) && (idArray==null||idArray.size() <=0)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		int delete = spProductService.updateProducts(idArray,Integer.valueOf(storeId));
		if(delete <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return  result;
	}
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public ApiResponse saveProduct(SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		product.setCreateBy((String) httpSession.getAttribute("clientId"));
		product.setModifyBy((String) httpSession.getAttribute("clientId"));
		
		product.setId(UUID.randomUUID().toString());
		int save = spProductService.saveProduct(product);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return  result;
	}
	@RequestMapping("/takingStock")
	@ResponseBody
	public ApiResponse takingStock(SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		product.setCreateBy((String) httpSession.getAttribute("clientId"));
		product.setModifyBy((String) httpSession.getAttribute("clientId"));
		
		List<SsStock> stocks = spProductService.takingStock(product);
		if(stocks == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(stocks);
		}
		return  result;
	}
	@RequestMapping("/getImages")
	@ResponseBody
	public ApiResponse getImages(SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		product.setCreateBy((String) httpSession.getAttribute("clientId"));
		product.setModifyBy((String) httpSession.getAttribute("clientId"));
		
		List<SsProductFile> files = spProductService.getImages(product);
		if(files == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(files);
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
		
		String pathOriginal = "/touchart/original/"+d+"/"; //原图路径

		String pathReduce  = "/touchart/reduce/"+d+"/"; //缩略图路径
		InputStream input;
		try {
			input = file.getInputStream();
			boolean upload = FTPClientUtils.getInstance().uploadFileToFtp(pathOriginal, fileName, input);
			  DiskFileItem fileItem = (DiskFileItem) file.getFileItem();
			  File f = ImageUtils.compressionFile(fileItem.getStoreLocation(),fileName);
			  boolean upload1 = FTPClientUtils.getInstance().uploadFileToFtp(pathReduce, fileName,new FileInputStream(f));
			if(upload){
				SsProductFile image = new SsProductFile();
				image.setFilename(file.getOriginalFilename());
				image.setUrl(pathOriginal+fileName);
				image.setResizeFixUrl(pathReduce+fileName);
				result.setData(image);
			}else{
				result.setCode(ResponseInfo.EXCEPTION.code);
				result.setMsg(ResponseInfo.EXCEPTION.msg);
			}
		} catch (Exception e) {

			logger.error("FTP 文件上传错误："+e.getMessage());

		} 
		return  result;
	}

	
	
}
