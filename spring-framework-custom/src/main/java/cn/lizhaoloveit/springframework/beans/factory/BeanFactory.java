package cn.lizhaoloveit.springframework.beans.factory;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:04
 */
public interface BeanFactory {
    Object getBeans(String beanName);
    <T> T getBeans(Class<T> clazz);
}
