package costumetrade.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpSession;
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{
	 @Override  
	    public boolean beforeHandshake(ServerHttpRequest request,  
	            ServerHttpResponse response, WebSocketHandler wsHandler,  
	            Map<String, Object> attributes) throws Exception {  
	        System.out.println("Before Handshake"); 
	        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
	        String fUserName = servletRequest.getServletRequest().getParameter("fUserName");
	        String tUserName = servletRequest.getServletRequest().getParameter("tUserName");
	        System.out.println("fUserName:"+fUserName+"tUserName:"+tUserName);
	        attributes.put("fUserName",fUserName);
	        attributes.put("tUserName",tUserName);
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//                //使用userName区分WebSocketHandler，以便定向发送消息
//                String fUserName = (String) session.getAttribute("fUserName");
//                if (fUserName==null) {
//                	fUserName="default-system";
//                }
//                System.out.println("far ::"+fUserName);
//                attributes.put("fUserName",fUserName);
//                
//            }
	       
	       
	        return true;  
	    }  
	  
	    @Override  
	    public void afterHandshake(ServerHttpRequest request,  
	            ServerHttpResponse response, WebSocketHandler wsHandler,  
	            Exception ex) {  
	        System.out.println("After Handshake");  
	        
	        super.afterHandshake(request, response, wsHandler, ex);  
	    }  
}
