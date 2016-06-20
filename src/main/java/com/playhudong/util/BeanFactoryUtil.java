package com.playhudong.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * A factory singleton to get a bean by name,
 * mainly used in concurrent environments,
 * cause Spring cannot inject a bean into another
 * thread.
 * @author arlabsurface
 *
 */
@Component("beanFactoryUtil")
public class BeanFactoryUtil implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;
	private static BeanFactoryUtil factoryUtil = null;
	
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		BeanFactoryUtil.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public static BeanFactoryUtil getInstance() {
		if (factoryUtil == null) {
			synchronized (factoryUtil) {
				if(factoryUtil == null)
					factoryUtil = new BeanFactoryUtil();
			}
		}
		return factoryUtil;
	}
	
	public static Object getBean(String name) {
		return beanFactory.getBean(name);
	}

}
