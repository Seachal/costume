/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import costumetrade.common.conf.SystemProperties;
import costumetrade.common.login.LoginContext;
import costumetrade.common.login.SupportTempLogin;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.sms.SMSTemplate;
import costumetrade.common.util.IPUtils;
import costumetrade.common.util.PhoneFormatCheckUtils;

/**
 * @author zhouyq
 * @Date 2017年3月15日
 */
@Controller
public class SmsController {
	
	private static final String MOBLILE_CACHEKEY=SystemProperties.name+"sms.mobile.duration.";
	
	private static final String USER_CACHEKEY=SystemProperties.name+"sms.user.duration";
	
	private static final String IP_CACHEKEY=SystemProperties.name+"sms.ip";
	
	@Autowired
	private ICaptchaService captchaService;
	
	@Autowired
	private ISmsService smsService;

	//@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@Value("${sms.ip.minute:50}")
	private  Integer ipMinuteSize;
	
	@Value("${sms.mobile.day:10}")
	private  Integer mobileDaySize;
	
	@SupportTempLogin
	@RequestMapping("/sms/send")
	public ApiResponse send(String businessKey,String mobile,String captchaCode,HttpServletRequest request){
		
		if(StringUtils.isBlank(businessKey)){
			throw new IllegalArgumentException("业务key不能为空");
		}
		
		if(StringUtils.isBlank(mobile)){
			throw new IllegalArgumentException("手机号不能为空");
		}
		
		if(StringUtils.isBlank(captchaCode)){
			throw new IllegalArgumentException("图片验证码不能为空");
		}
		
		if(PhoneFormatCheckUtils.isPhoneLegal(mobile)){
			throw new IllegalArgumentException("手机号格式不对："+mobile);
		}
		
		if(StringUtils.isEmpty(LoginContext.getAccount())){
			return ApiResponse.UNAUTHORIZED;
		}
		
		if(!captchaService.valid(businessKey, captchaCode)){
			return new ApiResponse(6001,"图片验证码错误");
		}
		
		//（手机限制）单个手机60秒重复发送
		Long mills = (Long) redisTemplate.opsForValue().get(MOBLILE_CACHEKEY+mobile);
		if(mills!=null){
			throw new RuntimeException("请在60秒后在请求");
		}
	
		//(ip限制) 单IP一分钟限制条数
		String ip = IPUtils.getClientIpAddress(request);
		if(ipExceedLimit(ip)){
			throw new RuntimeException(String.format("ip%s每分钟发送短信频次超过%s",ip,ipMinuteSize));
		}
		
		//（用户限制）单个手机60秒重复发送
		String account = LoginContext.getAccount();
		Long mobileMilis = (Long) redisTemplate.opsForValue().get(USER_CACHEKEY+account);
		if(mobileMilis!=null){
			throw new RuntimeException("请在60秒后在请求");
		}
		
		String message = SMSTemplate.getTemplate(businessKey);
		String code = RandomStringUtils.randomNumeric(6);
		smsService.send(mobile,MessageFormat.format(message, code));
		smsService.save(businessKey, code);
		
		redisTemplate.opsForValue().set(MOBLILE_CACHEKEY+mobile,System.currentTimeMillis(),60,TimeUnit.SECONDS);
		
		redisTemplate.opsForValue().set(USER_CACHEKEY+account,System.currentTimeMillis(),60,TimeUnit.SECONDS);
		
		
		redisTemplate.opsForList().trim(IP_CACHEKEY+ip, 0, (long) (ipMinuteSize*1.1));
		redisTemplate.opsForList().leftPush(IP_CACHEKEY+ip, System.currentTimeMillis());
		
		return ApiResponse.SUCCESS;
	}

	private boolean ipExceedLimit(String ip) {
		
		if(redisTemplate.opsForList().size("costumetrade.sms.ip."+ip)>=ipMinuteSize){
			Long millis = (Long) redisTemplate.opsForList().index(IP_CACHEKEY+ip, ipMinuteSize-1);
			return millis+60000>System.currentTimeMillis();
		}
		return false;
	}
	
}
