package costumetrade.websocket.domain;

import costumetrade.common.Entity;

/**
 * 聊天消息
 * 
 * @author Fancy
 * 
 */
public class ChatMessage extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String username;
	private String timeSign;
	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTimeSign() {
		return timeSign;
	}

	public void setTimeSign(String timeSign) {
		this.timeSign = timeSign;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
