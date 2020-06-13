package cn.lizhaoloveit.springframework.beans.factory;

import cn.lizhaoloveit.springframework.beans.config.BeanDefinition;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:07
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    @Override
    public <T> T getBeans(Class<T> clazz) {
        return null;
    }
    @Override
    public Object getBeans(String beanName) {
        return null;
    }
    public abstract void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    public abstract void registerBeanDefinitionClz(String simpleName, BeanDefinition beanDefinition);
}
