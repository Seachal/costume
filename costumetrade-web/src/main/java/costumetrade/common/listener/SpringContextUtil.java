package costumetrade.common.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException(
                "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
        return applicationContext;
    }
	
	
	/**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
}
