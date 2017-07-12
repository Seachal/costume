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
		 try {
			 System.out.println("Before Handshake"); 
		        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		        String storeId = servletRequest.getServletRequest().getParameter("storeId");
		        attributes.put("storeId", storeId);

		        System.out.println("storeId:"+storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
	       
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
