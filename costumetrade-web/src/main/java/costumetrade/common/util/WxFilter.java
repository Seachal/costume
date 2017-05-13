package costumetrade.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class WxFilter implements Filter {
	
	private FilterConfig config;

	/**
	 * 微信过滤器，将openId和toUser(不同接口传的参数,都指客户的微信openId)添加到cookie中
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String openId = request.getParameter("openId");
		String toUser = request.getParameter("toUser");
		String cookieOpenId = getCookieOpenId(request);
		String fromUser = null;

		// 如果cookie里面有openId，使用它；如果没有使用传入的openId，并且放入cookie
		if (!StringUtils.isEmpty(cookieOpenId)) {
			fromUser = cookieOpenId;
		} else {
			if (!StringUtils.isEmpty(openId)) {
				fromUser = openId;
			} else if (!StringUtils.isEmpty(toUser)) {
				fromUser = toUser;
			}
			if (!StringUtils.isEmpty(fromUser)) {
				Cookie cookie = new Cookie("openId", fromUser);
				cookie.setMaxAge(3600);
				((HttpServletResponse) response).addCookie(cookie);
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	public void destroy() {
		config = null;
	}

	public static String getCookieOpenId(ServletRequest request) {
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if (cookies == null || cookies.length == 0) {
			return "";
		}
		for (Cookie cook : cookies) {
			if ("openId".equalsIgnoreCase(cook.getName())) {
				return cook.getValue();
			}
		}
		return "";
	}
}
