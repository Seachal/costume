/**
 * Copyright (C) 2014-2015, Utry and/or its affiliates. All rights reserved.
 * Utry PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * ApplicationContext静态工具类
 * 在applicationContext还未注入式，会出现空指针。为了避免这个问题推荐采用下面的方法注入ApplicationContext： 
 * <code>
 *  {@literal @}Autowire
 *  private ApplicationContext applicationContext;
 * </code>
 * </pre>
 * @author zhouyq
 * @Date 2015年10月8日
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		ApplicationContextUtil.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getContext(){
		
		return applicationContext;
	}
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
		
	}

	/**
	 *	@author zhouyq
	 *  @return
	 */
	public static <T> T getBean(Class<T> requiredType ) {
		
		return applicationContext.getBean(requiredType);
	}

    /**
     * @author zhouyq
     * @param beanName
     */
    public static boolean containsBean(String beanName) {
        
        return applicationContext.containsBean(beanName);
    }

}
