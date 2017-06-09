package costumetrade.user.control;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;

import costumetrade.common.util.SerializeXmlUtil;
import costumetrade.order.domain.SpClient;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.InputMessage;
import costumetrade.user.domain.OutputMessage;
import costumetrade.user.domain.QRCodeScanParam;


@Controller
public class WeiXinCallBackAttentionController {
	@Autowired
	private WeChatService weChatService;
		/**
		 * 微信验证回调
		 * @param request
		 * @param response
		 * 
		 * 
		 * <xml><ToUserName><![CDATA[gh_2a5540516edb]]></ToUserName>
			<FromUserName><![CDATA[odwP2jt6aTBu_Dl1ypoUJ8pN9UOs]]></FromUserName>
			<CreateTime>1425633941</CreateTime>
			<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[SCAN]]></Event>
			<EventKey><![CDATA[0]]></EventKey>
			<Ticket><![CDATA[gQGK8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2RIVlBsRVBsdEhITk9jQzRaMXV6AAIEgXL5VAMECAcAAA==]]></Ticket>
			</xml>
		 */
		@RequestMapping(value ="/", method={RequestMethod.POST,RequestMethod.GET})
		public void callback(String body,HttpServletRequest request,HttpServletResponse response){
				
		        System.out.println("================================微信URL回调测试=========================");  
		      
		        // 处理接收消息  
		        ServletInputStream in;
		        XStream xs = new XStream();
		        StringBuilder xmlMsg = new StringBuilder();
				try {
					in = request.getInputStream();
					// 将POST流转换为XStream对象  
			        xs = SerializeXmlUtil.createXstream();  
			        xs.processAnnotations(InputMessage.class);  
			        xs.processAnnotations(OutputMessage.class);  
			        // 将指定节点下的xml节点数据映射为对象  
			        xs.alias("xml", InputMessage.class);  
			        // 将流转换为字符串  
			        xmlMsg = new StringBuilder();  
			        byte[] b = new byte[4096];  
			        for (int n; (n = in.read(b)) != -1;) {  
			            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
			        }  
			     // 将xml内容转换为InputMessage对象  
			        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());  
			        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间  
			        // 取得消息类型  
			        String msgType = inputMsg.getMsgType();  
			        System.out.println("inputMsg.getEvent():"+inputMsg.getEvent());
			        // 根据消息类型获取对应的消息内容  
			        if (msgType.equals("event")) {  // 文本消息  
			            if("SCAN".equals(inputMsg.getEvent())||"subscribe".equals(inputMsg.getEvent())){//已关注事件推送，未关注公众号事件推送
			            	weChatService.bindOpenidScan(inputMsg);
			            }
			            OutputMessage outputMsg = new OutputMessage();  
			            outputMsg.setFromUserName(inputMsg.getToUserName());  
			            outputMsg.setToUserName(inputMsg.getFromUserName());  
			            outputMsg.setCreateTime(returnTime);  
			            outputMsg.setMsgType("text");  
			            outputMsg.setContent("欢迎来到公众号！");

			            System.out.println("xml转换：/n" + xs.toXML(outputMsg));  
			            response.getWriter().write(xs.toXML(outputMsg)); 
			        }  
				} catch (IOException e) {
					e.printStackTrace();
				}  
		        
		        
//		        // 获取并返回多图片消息  
//		        if (msgType.equals(MsgType.Image.toString())) {  
//		            System.out.println("获取多媒体信息");  
//		            System.out.println("多媒体文件id：" + inputMsg.getMediaId());  
//		            System.out.println("图片链接：" + inputMsg.getPicUrl());  
//		            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());  
//		  
//		            OutputMessage outputMsg = new OutputMessage();  
//		            outputMsg.setFromUserName(servername);  
//		            outputMsg.setToUserName(custermname);  
//		            outputMsg.setCreateTime(returnTime);  
//		            outputMsg.setMsgType(msgType);  
//		           // ImageMessage images = new ImageMessage();  
////		            images.setMediaId(inputMsg.getMediaId());  
////		            outputMsg.setImage(images);  
//		            System.out.println("xml转换：/n" + xs.toXML(outputMsg));  
//		            response.getWriter().write(xs.toXML(outputMsg)); 
				
//		  		 StringBuffer str = new StringBuffer();  
//	            str.append("<xml>");  
//	            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");  
//	            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");  
//	            str.append("<CreateTime>" + returnTime + "</CreateTime>");  
//	            str.append("<MsgType><![CDATA[text]]></MsgType>");  
//	            str.append("<Content><![CDATA[欢迎来到公众号！您的openID："+servername+"]]></Content>");  
//	            str.append("</xml>");  
//	            System.out.println(str.toString());  
//	            response.getWriter().write(str.toString()); 
//		        }  
		/*	
		 * 微信回调验证
		 * 
		 *
		 * 
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			//echostr = "fancywexin";
			String token = "fancywexin";
			
			PrintWriter out;
			
			try {
				out = response.getWriter();
				out.println(echostr);
				out.close();
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
	}
		
	
}

//import java.io.IOException;  
//import java.io.PrintWriter;  
//import java.security.MessageDigest;  
//import java.util.Arrays;  
//  
//import javax.servlet.ServletException;  
//import javax.servlet.annotation.WebServlet;  
//import javax.servlet.http.HttpServlet;  
//import javax.servlet.http.HttpServletRequest;  
//import javax.servlet.http.HttpServletResponse;  
//  
//import org.apache.commons.codec.binary.Hex;  
//  
//@WebServlet(urlPatterns = "/wx/housekeeper", name = "wxHouseKeeperServlet")  
//public class WeiXinCallBackAttentionController extends HttpServlet {  
//    public static final String TOKEN = "fancywexin";  
//  
//    @Override  
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
//            throws ServletException, IOException {  
//        try {  
//        	System.out.println("weixin ##########weixin ");
//            // 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数  
//            String signature = request.getParameter("signature");// 微信加密签名（token、timestamp、nonce。）  
//            String timestamp = request.getParameter("timestamp");// 时间戳  
//            String nonce = request.getParameter("nonce");// 随机数  
//            String echostr = request.getParameter("echostr");// 随机字符串  
//            PrintWriter out = response.getWriter();  
//            // 将token、timestamp、nonce三个参数进行字典序排序  
//            String[] params = new String[] { TOKEN, timestamp, nonce };  
//            Arrays.sort(params);  
//            // 将三个参数字符串拼接成一个字符串进行sha1加密  
//            String clearText = params[0] + params[1] + params[2];  
//            String algorithm = "SHA-1";  
//            String sign = new String(  
//                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));  
//            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信  
//            if (signature.equals(sign)) {  
//                response.getWriter().print(echostr);  
//            }  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(req, resp);
//	

    

