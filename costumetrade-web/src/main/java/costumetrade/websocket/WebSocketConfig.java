package costumetrade.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import costumetrade.common.util.ConfigProperties;

@Configuration 

@EnableWebSocket 
public class WebSocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(),"/socketHander").addInterceptors(new HandshakeInterceptor()).setAllowedOrigins(ConfigProperties.getProperty("print.url"));  
        //registry.addHandler(systemWebSocketHandler(),"/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();
		
	}
	
	@Bean  
	public WebSocketHandler systemWebSocketHandler(){  
	    return new SystemWebSocketHandler();  
	} 

}
