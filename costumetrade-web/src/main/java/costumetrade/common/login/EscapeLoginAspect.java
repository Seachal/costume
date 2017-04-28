package costumetrade.common.login;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import costumetrade.common.util.IPUtils;

@Aspect
@Component
public class EscapeLoginAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EscapeLoginAspect.class);
	
	@Before("@annotation(costumetrade.common.login.EscapeLogin)")
	public void record(JoinPoint joinPoint){
		
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(sra==null){
			return;
		}
		HttpServletRequest request = sra.getRequest();
		LOGGER.warn("client[{}] acessed {}?{} without login",IPUtils.getClientIpAddress(request),request.getRequestURL().toString(),request.getQueryString());
	}
}
