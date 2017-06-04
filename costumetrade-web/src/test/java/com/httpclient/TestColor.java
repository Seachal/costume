/*
 * 系统名称: 得仕宝系统
 * 模块名称: 手机APP模块
 * 文件名称: TestHttpClientUtils.java
 * 软件版权: 恒生电子股份有限公司
 * 创建日期: 2014年6月3日 人员：jinxx<br>
 *
*/

package com.httpclient;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;


/**
 * 功能说明: 模拟httpclient请求
 * <p> 系统版本: v1.0<br>
 * 开发人员: email jinxx@hundsun.com <br>
 * 时间: 2014年6月3日 <br>
 */

public class TestColor {
  
    @Test
    public  void testSaveColor() throws ClientProtocolException, IOException{
        
      //  String url = "http://localhost:8080/color/saveColor";
        //String url = "http://localhost:8080/color/deleteColor";
       // String url = "http://localhost:8080/color/saveColorCustom";
        String url = "http://localhost:8080/color/deleteColorCustom";
        Map<String,String> paramMap  = new HashMap<String,String>();
//        paramMap.put("value", "红色,蓝色，紫色");
//        paramMap.put("customname","自定义1" );
//        paramMap.put("corpid", "100001");
        paramMap.put("id", "2");
        String str = HttpClientUtils.post(url, paramMap,"utf-8");
        System.out.println(str);
    }
/*    public static void post() throws IOException{
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<Request>"
                + "<Ver>1.0</Ver>"
                + "<SerialNum>20130415170908</SerialNum>"
                + "<ServiceId >F100</ServiceId>"
                + "<ReqTime>2014-09-10 15:30:30</ReqTime>"
                + "<Data/>"
                + "</Request>";
        String encode = URLEncoder.encode(xml,"utf-8");
        System.out.print(encode);
        String url = "http://localhost:8080/app/service.html";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("data", encode);
        paramMap.put("serviceid", "F100");
        String str = HttpClientUtils.post(url, paramMap,"utf-8");
        System.out.println(str);
    }*/
    
    @Test
    public  void testSaveSize() throws ClientProtocolException, IOException{
        
       //String url = "http://localhost:8080/size/saveSize";
        //String url = "http://localhost:8080/size/deleteSize";
        //String url = "http://localhost:8080/size/saveSizeCustom";
       // String url = "http://localhost:8080/size/deleteSizeCustom";
        //String url = "http://localhost:8080/brand/saveBrand";
        String url = "http://localhost:8080/brand/deleteBrand";
       // String url = "http://localhost:8080/size/deleteSizeCustom";
        Map<String,String> paramMap  = new HashMap<String,String>();
        //paramMap.put("sizename", "S");
       // paramMap.put("barcode","s" );
//          paramMap.put("corpid", "100001");
//          paramMap.put("brandname", "珂卡芙");
//        paramMap.put("value", "S,M,L");

        paramMap.put("id", "1");
        String str = HttpClientUtils.post(url, paramMap,"utf-8");
        System.out.println(str);
        
        
        
    }
    @Test
    public void testProducts(){
    	//String url = "http://localhost:8080/product/getProducts";
//    	Map<String,Object> paramMap  = new HashMap<String,Object>();
//    	//paramMap.put("code", "S");
//        paramMap.put("subId","100001" );
//        paramMap.put("corpId", "100001");
//        paramMap.put("TimeUpOp", "");
//        paramMap.put("priceOp", "asc");
//        paramMap.put("productBrand", "珂卡芙, 红袖");
//        paramMap.put("productSeason", "夏,春");
       // paramMap.put("productType", "连衣裙");
        
        
        
       // String url = "http://localhost:8080/product/getProductInit";
        String url = "http://localhost:8080/cart/saveCart";
        Map<String,String> paramMap1  = new HashMap<String,String>();
        paramMap1.put("corpid", "100001");
        paramMap1.put("productid", "1");
        paramMap1.put("productname", "cedf");
        paramMap1.put("image", "ferwytru5475658");
        paramMap1.put("color", "1");
        paramMap1.put("size", "3");
        paramMap1.put("price", "213");
        paramMap1.put("count", "1");
        
        
        
//        String url = "http://localhost:8080/product/saveProduct";
//        Map<String,String> paramMap1   = new HashMap<String,String>();
//        paramMap1.put("corpid", "100001");
//        paramMap1.put("code", "20170503");
//        paramMap1.put("brandid", "1");
//        paramMap1.put("prducttype", "3");
//        paramMap1.put("name", "短脚裤");
//        paramMap1.put("grade", "二级");
//        paramMap1.put("unit", "件");
//        paramMap1.put("colors", "红色,黄色,白色");
//        paramMap1.put("sizes", "2");
//        paramMap1.put("subid", "100001");
//        paramMap1.put("handcount", "3");
//        paramMap1.put("season", "秋");
//        paramMap1.put("stock", "180");
//        paramMap1.put("sale0", "432");
//        paramMap1.put("sale1", "45");
//        paramMap1.put("sale2", "23");
//        paramMap1.put("sale3", "567");
//        paramMap1.put("timeUp", new Date().toString());
//        paramMap1.put("timeDown", new Date().toString());
        //String url = "http://localhost:8080/product/getProductInit";
        //paramMap1  = new HashMap<String,String>();
        String str;
		try {
			//str = HttpClientUtils.postDo(url, paramMap,"utf-8");
			
			str = HttpClientUtils.post(url, paramMap1,"utf-8");
			System.out.println(str);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
//    @Test
//    public void testOrder(){
//    	String url = "http://localhost:8080/order/saveOrders";
//    	SpStoOrder order = new SpStoOrder();
//    	order.setBuyerid(213);
//    	order.setBuyersubid(123);
//    	order.setCorpid(100001);
//    	order.setOrderstatus(1);
//    	order.setOrderTime(new Date());
//    	order.setOrdertype("1");
//    	
//    	//List<SpStoDetail> detail = new ArrayList<SpStoDetail>();
//    	SpStoDetail[] detail = new SpStoDetail[2];
//    	for(int i=0 ; i< 2; i++){
//    		SpStoDetail d = new SpStoDetail();
//    		d.setPrice(new BigDecimal(123));
//    		d.setProductcolor("fgd");
//    		d.setProductid(i+1+"");
//    		d.setProductname("衬衣123445");
//    		d.setProductsize("S");
//    		d.setProductunit("件");
//    		detail[i]=d;
//    	}
//    	OrderQuery query = new OrderQuery();
//    	query.setDetail(detail);
//    	query.setOrder(order);
//    	
//    	JSONObject json = new JSONObject();
//    	json.put("order", order);
//    	json.put("detail",detail);
//    	Map<String,String> paramMap  = new HashMap<String,String>();
//    	paramMap.put("query", json.toString());
//    	//paramMap.put("detail", detail.toString());
//    	String str;
//    	//HttpServletRequest req = new HttpServletReques;
//		try {
//			//str = HttpClientUtils.postDo(url, paramMap,"utf-8");
//			str = HttpClientUtils.post(url, paramMap,"utf-8");
//			//str = HttpPostUtil.getRequestMapData(null);
//			System.out.println(str.toString());
//		}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    @Test
    public void getOrder(){
    	Map<String,String> paramMap  = new HashMap<String,String>();
    	//String url = "http://localhost:8080/order/getOrder";
    	//String url = "http://localhost:8080/order/orderCancel";
    	//String url = "http://localhost:8080/order/orderAudit";
    	String url = "http://localhost:8080/order/orderPay";
    	paramMap.put("corpId", "100001");
        paramMap.put("orderNo", "1493897533735");
        paramMap.put("operate", "2");
        paramMap.put("financialTag", "3");
        paramMap.put("orderNo", "1493897533735");
        paramMap.put("buyerClientId", "1243");
        paramMap.put("sellerClientId", "214");
        paramMap.put("income", "321.56");
        paramMap.put("pay", "123.78");
        paramMap.put("relaId", "1");
        paramMap.put("payDate", new Date().toString());
        paramMap.put("partnerCardno", "14225133153225335454414");
        paramMap.put("payBank", "1");
        paramMap.put("payType", "1");
        String str;
        try {
			str = HttpClientUtils.post(url, paramMap,"utf-8");
			//str = HttpPostUtil.getRequestMapData(null);
			System.out.println(str.toString());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
