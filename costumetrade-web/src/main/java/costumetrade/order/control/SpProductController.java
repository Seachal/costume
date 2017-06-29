package costumetrade.order.control;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.FTPClientUtils;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsProductFile;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.domain.SsStock;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;
import costumetrade.order.service.WeChatService;
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
	private SpProductService spProductService;
	@Autowired
	private WeChatService weChatService;

	@RequestMapping("/getProducts")
	@ResponseBody
	public ApiResponse getAllroducts(@RequestBody ProductQuery paramProduct) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(paramProduct == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		if(paramProduct.getStatus() == null){
			paramProduct.setStatus(0);
		}
		if(StringUtils.isNotBlank(paramProduct.getCode())&&StringUtils.isNotBlank(paramProduct.getName())
				&&paramProduct.getCode().equals(paramProduct.getName())){
			paramProduct.setCodeAndName("true");
		}
		List<ProductQuery> productLists = spProductService.selectProducts(paramProduct);
	
		if(productLists ==null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		return  ApiResponse.getInstance(productLists);
	}
	
	@RequestMapping("/getProductDetail")
	@ResponseBody
	public ApiResponse getProduct(ProductQuery paramProduct) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(StringUtils.isBlank(paramProduct.getStoreId()+"")||StringUtils.isBlank(paramProduct.getId()+"")){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		ProductQuery product = spProductService.selectProduct(paramProduct);
		return  ApiResponse.getInstance(product);
	}
	
	@RequestMapping("/getProductInit")
	@ResponseBody
	public ApiResponse getProductInit(ProductQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(query.getStoreId()+"")){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		ProductQuery q = spProductService.productInit(query);
		return  ApiResponse.getInstance(q);
	}
	
	@RequestMapping("/getShareProduct")
	@ResponseBody
	public ApiResponse getShareProduct(ProductQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(query.getStoreId()+"")){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		String openid = null;
		if(StringUtil.isNotBlank(query.getAppId())&&StringUtil.isNotBlank(query.getAppSecret())&&StringUtil.isNotBlank(query.getCode())){
			String openIdAndKey;
			try {
				openIdAndKey = weChatService.getOpenIdAndKey(query.getCode(),query.getAppId(),query.getAppSecret());
				JSONObject json = JSON.parseObject(openIdAndKey);
				openid = json.getString("openid");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		List<Object> obj = new ArrayList<Object>();
		obj.add(openid);
		
		List<ProductQuery> q= spProductService.getShareProduct(query);
		obj.add(q);
		
		return  ApiResponse.getInstance(q);
	}
	
	@RequestMapping("/makePopularize")
	@ResponseBody
	public ApiResponse makePopularize(ProductQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		Integer  q= spProductService.makePopularize(query);
		if(q == null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return  result;
	}
	
	
	
	@RequestMapping("/updateProductInit")
	@ResponseBody
	public ApiResponse updateProductInit(ProductQuery  productQuery ) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(productQuery.getStoreId()==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		ProductQuery query = spProductService.updateProductInit(productQuery);
		if(query == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(query);
		}
		return  result;
	}
	@RequestMapping("/patternAddPriceInit")
	@ResponseBody
	public ApiResponse patternAddPriceInit(ProductQuery  productQuery ) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(productQuery.getStoreId()==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<Object> objects = spProductService.patternAddPriceInit(productQuery);
		if(objects == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(objects);
		}
		return  result;
	}
	
	@RequestMapping("/savePatternAddPrice")
	@ResponseBody
	public ApiResponse savePatternAddPrice(@RequestBody ProductQuery  productQuery ) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int save = spProductService.savePatternAddPrice(productQuery);
		if(save <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return  result;
	}
	
	
	@RequestMapping("/updateProducts")
	@ResponseBody
	public ApiResponse updateProducts(ProductQuery  productQuery ) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(productQuery.getStoreId()==null&& (productQuery.getIdArray()==null||productQuery.getIdArray().size() <=0)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int update = spProductService.updateProducts(productQuery);
		if(update <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return  result;
	}
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public ApiResponse saveProduct(@RequestBody SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(product == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		String id = spProductService.saveProduct(product);
		if(id==null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData( id);
		}
		
		return  result;
	}
	
	@RequestMapping("/takingStock")
	@ResponseBody
	public ApiResponse takingStock(@RequestBody SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		List<SsStock> stocks = spProductService.takingStock(product);
		if(stocks == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(stocks);
		}
		return  result;
	}
	
	@RequestMapping("/updateStock")
	@ResponseBody
	public ApiResponse updateStock(@RequestBody List<SsStock> stocks) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(stocks == null ||(stocks!=null&&stocks.size()<=0)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		Integer stockList = spProductService.updateStock(stocks);
		if(stockList == null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(stockList);
		}
		return  result;
	}
	@RequestMapping("/getImages")
	@ResponseBody
	public ApiResponse getImages(SsProductFile productFile) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<SsProductFile> files = spProductService.getImages(productFile);
		if(files == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(files);
		}
		return  result;
	}
	@RequestMapping("/getReviews")
	@ResponseBody
	public ApiResponse getReviews(ProductQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SsProductReview> reviews = spProductService.getReviews(query);
		if(reviews == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(reviews);
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
		//获取上传文件后缀
		String fileName = file.getOriginalFilename();
		String postfix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		List<String> videoPostfix = new ArrayList<String>();//视频后缀
		videoPostfix.add("avi");
		videoPostfix.add("mpg");
		videoPostfix.add("mpeg");
		videoPostfix.add("rmvb");
		videoPostfix.add("rm");
		videoPostfix.add("wmv");
		videoPostfix.add("mp4");
		
		List<String> imagePostfix = new ArrayList<String>();//图片后缀
		imagePostfix.add("bmp");
		imagePostfix.add("gif");
		imagePostfix.add("jpg");
		imagePostfix.add("jpeg");
		imagePostfix.add("png");
		imagePostfix.add("pic");
		
		UUID uuid=UUID.randomUUID();
        String str = uuid.toString().replaceAll("\\-", ""); 
		fileName =str+fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String commonUrl="/touchartImage/imageTomcat/apache-tomcat-7.0.76/webapps/ROOT";
		String pathOriginal = "/original/"+d+"/"; //原图路径
		String pathReduce  = "/reduce/"+d+"/"; //缩略图路径
		String video ="/video/"+d+"/";
		InputStream input;
		//ZipInputStream zipInput;
	
		try {
			SsProductFile image = new SsProductFile();
			input = file.getInputStream();
			//zipInput=new ZipInputStream(new BufferedInputStream(input));
			boolean upload =false;
			if(imagePostfix.contains(postfix)){
				upload = FTPClientUtils.getInstance().uploadFileToFtp(commonUrl+pathOriginal, fileName, input);
//				DiskFileItem fileItem = (DiskFileItem) file.getFileItem();
//				File f = ImageUtils.compressionFile(fileItem.getStoreLocation(),fileName);
//				FTPClientUtils.getInstance().uploadFileToFtp(commonUrl+pathReduce, fileName,new FileInputStream(f));
				
				image.setFilename(file.getOriginalFilename());
				image.setUrl(pathOriginal+fileName);
				image.setResizeFixUrl(pathReduce+fileName);
			}else if(videoPostfix.contains(postfix)){
				upload = FTPClientUtils.getInstance().uploadFileToFtp(commonUrl+video, fileName, input);
				image.setFilename(file.getOriginalFilename());
				image.setUrl(video+fileName);
			}else{
				result.setCode(ResponseInfo.FILE_FORMAT.code);
				result.setMsg(ResponseInfo.FILE_FORMAT.msg);
			}
			if(upload){
				result.setData(image);
			}else{
				result.setCode(ResponseInfo.UPLOAD_EXCEPTION.code);
				result.setMsg(ResponseInfo.UPLOAD_EXCEPTION.msg);
			}
		} catch (Exception e) {
			result.setCode(ResponseInfo.UPLOAD_EXCEPTION.code);
			result.setMsg(ResponseInfo.UPLOAD_EXCEPTION.msg);
			logger.error("FTP 文件上传错误："+e.getMessage());
		} 
		return  result;
	}
	
	@RequestMapping("/downloadVideo")
	@ResponseBody
	public ApiResponse downloadVideo(String fileUrl) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//设置上传图片路径       upload/日期/
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String date = format.format(new Date());
		Integer d = Integer.valueOf(date);
		//获取上传文件后缀
		String[] fileNames = new String[1];
		fileNames[0] = fileUrl.substring(fileUrl.lastIndexOf("/"), fileUrl.length());
	
		String commonUrl="/touchartImage/imageTomcat/apache-tomcat-7.0.76/webapps/ROOT";
	
		String video =fileUrl;
	
		FTPClientUtils.getInstance().downFileFromFtp(commonUrl+video, fileNames);
		return  result;
	}
	
	
}
