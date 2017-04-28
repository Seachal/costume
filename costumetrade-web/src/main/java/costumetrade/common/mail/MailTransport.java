/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年10月22日
 */
package costumetrade.common.mail;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 邮件发送功能
 * @author liyb
 * @version MailTransport.java,2015年10月22日 下午5:41:47
 */
public class MailTransport implements Serializable {

    private static final long serialVersionUID = 5540816975832840477L;
    private static final Logger logger = Logger.getAnonymousLogger();
    private MimeMessage       mimeMsg;                                //MIME邮件对象  
    private Session           session;                                //邮件会话对象
    private Properties        props;                                  //系统属性
    private Multipart         mp;                                     //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象 
    //smtp认证用户名和密码 
    private String            username;                               //用户名
    private String            password;                               //密码
    private String            smtpHost;                               //SMTP主机
    private String            needAuth;                               //SMTP是否需要验证

    /** 
     * Constructor 
     */
    public MailTransport() {
        setSmtpHost();
        createMimeMessage();
    }

    /** 
     * 设置邮件发送服务器 
     */
    public void setSmtpHost() {
        smtpHost = "smtp." + username.split("@")[1];
        logger.info("设置系统属性：mail.smtp.host = " + smtpHost);
        if (props == null)
            props = System.getProperties(); //获得系统属性对象   
        props.put("mail.smtp.host", smtpHost); //设置SMTP主机   
    }

    /** 
     * 设置SMTP是否需要验证 
     * @param need 
     */
    public void setNeedAuth() {
        logger.info("设置smtp身份认证：mail.smtp.auth = " + needAuth);
        if (props == null)
            props = System.getProperties();
        props.put("mail.smtp.auth", needAuth);
    }

    /** 
     * 创建MIME邮件对象   
     * @return 
     */
    public boolean createMimeMessage() {
        try {
            logger.info("准备获取邮件会话对象！");
//            session = Session.getDefaultInstance(props, null);//获得邮件会话对象 
            session = Session.getDefaultInstance(props, new MailAuthenticator(username, password));//获得邮件会话对象  
        } catch (Exception e) {
            logger.info("获取邮件会话对象时发生错误！" + e);
            return false;
        }
        logger.info("准备创建MIME邮件对象！");
        try {
            mimeMsg = new MimeMessage(session); //创建MIME邮件对象
            mp = new MimeMultipart();
            return true;
        } catch (Exception e) {
            logger.info("创建MIME邮件对象失败！" + e);
            return false;
        }
    }

    /** 
     * 设置邮件主题 
     * @param mailSubject 
     * @return
     */
    public boolean setSubject(String mailSubject) {
        logger.info("设置邮件主题！");
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            logger.info("设置邮件主题发生错误！");
            return false;
        }
    }

    /**  
     * 设置邮件正文 
     * @param mailBody String  
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + mailBody, "text/html;charset=GBK");
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            logger.info("设置邮件正文时发生错误！" + e);
            return false;
        }
    }

    /**  
     * 添加附件 
     * @param filename String  
     */
    public boolean addFileAffix(String filename) {
        logger.info("增加邮件附件：" + filename);
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(fileds.getName());
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            logger.info("增加邮件附件：" + filename + "发生错误！" + e);
            return false;
        }
    }

    /**
     * 设置发信人 
     * @param from String  
     */
    public boolean setFrom() {
        logger.info("设置发信人！");
        try {
            mimeMsg.setFrom(new InternetAddress(username)); //设置发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**  
     * 设置收信人 
     * @param to String  
     */
    public boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**  
     * 发送邮件 
     */
    public boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            logger.info("正在发送邮件....");
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
//            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
//            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
            transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
//            transport.send(mimeMsg);
            logger.info("发送邮件成功！");
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("邮件发送失败！" + e);
            return false;
        }
    }

    /**
     * 调用sendOut方法完成邮件发送，没有附件
     * @param to 发信人
     * @param subject 邮件主题
     * @param content 邮件正文
     * @return
     */
    public static boolean send(String to, String subject, String content) {
        MailTransport theMail = new MailTransport();
        theMail.setNeedAuth(); //需要验证 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom())
            return false;
        if (!theMail.sendOut())
            return false;
        return true;
    }
    
    /**
     * 发送邮件-必须带附件
     * @param to
     * @param subject
     * @param content
     * @param attachment
     * @return
     */
    public static boolean sendWithAttachment(String to, String subject, String content, String attachment){
    	MailTransport theMail = new MailTransport();
        theMail.setNeedAuth(); //需要验证 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if(!theMail.addFileAffix(attachment))
        	return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom())
            return false;
        if (!theMail.sendOut())
            return false;
        
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(String needAuth) {
        this.needAuth = needAuth;
    }
    
    public static void main(String[] args) {
        String to = "594323728@qq.com";  
        String subject = "测试邮件";  
        String content = "测试邮件发送内容"; 
        String att = "C:\\cardry\\zip\\test01-20160519165226.zip";
        MailTransport.sendWithAttachment(to, subject, content,att);
    }
}
