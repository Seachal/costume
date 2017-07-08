package costumetrade.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebSocket 
public class WebSocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(),"/socketHander").addInterceptors(new HandshakeInterceptor()).setAllowedOrigins("ws://192.168.2.221");  
        //registry.addHandler(systemWebSocketHandler(),"/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();
	}
	
	@Bean  
	public WebSocketHandler systemWebSocketHandler(){  
	    return new SystemWebSocketHandler();  
	} 

}
