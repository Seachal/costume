/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年10月23日
 */
package costumetrade.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 此类是用作登录校验的，以确保你对该邮箱有发送邮件的权利
 * @author liyb
 * @version MailAuthenticator.java,2015年10月23日 上午9:37:01
 */
public class MailAuthenticator extends Authenticator {
    private String username;
    private String password;

    /**
     * 初始化邮箱和密码
     * @param username 邮箱
     * @param password 密码
     */
    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
    }
}
