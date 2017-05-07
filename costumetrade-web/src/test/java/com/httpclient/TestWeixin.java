package com.httpclient;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class TestWeixin {
	@Autowired
	private WxCpService wxService;
	public static void main(String[] args) {
		WxCpMessage.WxArticle article = new WxCpMessage.WxArticle();
		String userIds ="";
		//wxService.messageSend(WxCpMessage.NEWS().agentId("1").toUser(userIds).addArticle(article).build());
		
	}

}
