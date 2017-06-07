package costumetrade.user.control;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WeiXinCallBackAttentionController {
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
		@RequestMapping(value ="/index", method={RequestMethod.POST,RequestMethod.GET})
		public void callback(HttpServletRequest request,HttpServletResponse response){
			
			//System.out.println(body.toString());
			System.out.println("================================微信URL回调测试=========================");
			SAXReader saxReader = new SAXReader();
			Document document;
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*	
		 * 微信回调验证
		 * 
		 *
		 * */
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

    

