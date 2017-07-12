package costumetrade.websocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import net.sf.json.JSONObject;

import org.jboss.logging.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler{
	private Logger log = Logger.getLogger(SystemWebSocketHandler.class);  
    
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
			throws Exception {
		users.remove(session);  
        log.debug("afterConnectionClosed" + closeStatus.getReason());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		System.out.println("ConnectionEstablished");  
        log.debug("ConnectionEstablished");  
        users.add(session);  
        //保存监听的打印机信息
        
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		System.out.println("handleMessage" + message.toString());  
        log.debug("handleMessage" + message.toString()); 
        String storeId= (String) session.getAttributes().get("sotreId");
        char seprator='\uffff';
        sendMessageToUser(storeId,new TextMessage("rptdata"+seprator+"门店销售"+seprator+"{\"id\":\"170312\",\"name\":\"123\"}"));   

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		if(session.isOpen()){  
            session.close();  
        }  
        users.remove(session);  
          
        log.debug("handleTransportError" + exception.getMessage()); 
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	 /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String storeId, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("storeId").equals(storeId)) {
            	System.out.println("发送消息啦！");
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
	/** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}
