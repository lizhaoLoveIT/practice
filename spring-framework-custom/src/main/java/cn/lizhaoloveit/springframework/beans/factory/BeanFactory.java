package cn.lizhaoloveit.springframework.beans.factory;

import cn.lizhaoloveit.springframework.beans.config.BeanDefinition;

import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:04
 */
public interface BeanFactory {
    Object getBean(String beanName);
    <T> T getBean(Class<T> clazz);
    /**
     * 根据指定bean的类型，获取对应的类型和子类型对应的bean实例
     *
     * @param type
     * @return
     */
    public <T> List<T> getBeansByType(Class<T> clazz);

    /**
     * 根据指定bean的类型，获取对应的类型和子类型对应的bean名称
     *
     * @param type
     * @return
     */
    public List<String> getBeanNamesByType(Class<?> type);

    public Map<String, BeanDefinition> getBeanDefiitions();
}
