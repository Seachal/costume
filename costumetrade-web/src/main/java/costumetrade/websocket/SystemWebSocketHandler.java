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
        String tUserName= (String) session.getAttributes().get("tUserName");
        String fUserName= (String) session.getAttributes().get("fUserName");
        System.out.println("fUserName111:"+fUserName);
        System.out.println("tUserName111:"+tUserName);
        users.add(session);  
        
        
//        session.sendMessage(new TextMessage("connect:{tUserName:"+tUserName+",fUserName:"+fUserName+",sendTime:"+new Date()+"}"));  
//        session.sendMessage(new TextMessage("new_msg"));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		System.out.println("handleMessage" + message.toString());  
        log.debug("handleMessage" + message.toString()); 
        String fUserName= (String) session.getAttributes().get("fUserName");
        System.out.println("fUserName:"+fUserName);
        System.out.println("fUserName:"+message.toString());
        sendMessageToUser(fUserName,new TextMessage(message.toString()));   
//        TextMessage mes = new TextMessage((CharSequence) message.getPayload());
        
//        String schatMessage = (String) message.getPayload();
//		System.out.println(schatMessage);
//		JSONObject jsonObject = JSONObject.fromObject(schatMessage);
//        session.sendMessage(new TextMessage(jsonObject + "")); 
//        session.sendMessage(new TextMessage(message + ""));
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
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("fUserName").equals(userName)) {
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
