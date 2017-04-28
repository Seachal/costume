/**
 * Copyright (C) 2014-2016, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.user.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import costumetrade.common.param.ApiResponse;


/**
 * 认证拦截器
 * 
 * @author zhouyq
 * @Date 2016年12月16日
 */
@Component
public class BasicAuthenticateInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticateInterceptor.class);

	private static final String AUTHORIZATION_PREFIX = "Basic ";
	
	private static final int expireTime = 324000;

	private static final int AUTHORIZATION_PREFIX_LENGTH = AUTHORIZATION_PREFIX.length();

	//@Autowired
	//private IUserService userservice;
	
	//@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

/*		String authorization = request.getHeader("Authorization");
		if (!authorization.startsWith(AUTHORIZATION_PREFIX)) {
			handleFailure(response, ApiResponse.AUTHENTICATION_FAILURE);
			return false;
		}

		String token = authorization.substring(AUTHORIZATION_PREFIX_LENGTH);
		
		LoginInfo loginInfo = (LoginInfo) redisTemplate.opsForValue().get(token);
		
		if(loginInfo==null){
			String[] usernamePasswordPair = new String(Base64.decodeBase64(token),StandardCharsets.UTF_8).split(":");
			PmsUser param = new PmsUser();
			param.setAccount(usernamePasswordPair[0]);
			param.setPassword(usernamePasswordPair[1]);
			List<PmsUser> users=  userservice.select(param);
			
			if(users.isEmpty()){
				LOGGER.error("认证失败,ip:{},username:{}",IPUtils.getClientIpAddress(),usernamePasswordPair[0]);
				handleFailure(response, ApiResponse.AUTHENTICATION_FAILURE);
				return false;
			}
			
			PmsUser user = users.get(0);

			loginInfo = new LoginInfo();
			loginInfo.setId(user.getId());
			loginInfo.setAccount(user.getAccount());
			loginInfo.setName(user.getName());
			
			redisTemplate.opsForValue().set(generateKey(user.getAccount(),user.getPassword()), loginInfo, expireTime, TimeUnit.SECONDS);
		}else{
			redisTemplate.expire(token, expireTime, TimeUnit.SECONDS);
		}*/
/*		Dept dept = new Dept();
		dept.setOrgId(62);
		dept.setDepth(1);
		dept.setId(1);
		LoginInfo loginInfo = new LoginInfo();
				  loginInfo.setId(16034);
				  loginInfo.setDept(dept);
				
				  

		LoginContext.put(loginInfo);*/

		return super.preHandle(request, response, handler);
	}
	
	private static String generateKey(String account,String password) {

		return Base64.encodeBase64URLSafeString((account+":"+password).getBytes(StandardCharsets.UTF_8));
	}

	private void handleFailure(HttpServletResponse response, ApiResponse apiResponse) throws IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		PrintWriter out = response.getWriter();
		out.println(JSON.toJSONString(apiResponse));
		out.flush();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//LoginContext.remove();
		super.postHandle(request, response, handler, modelAndView);
	}
	
	

}
