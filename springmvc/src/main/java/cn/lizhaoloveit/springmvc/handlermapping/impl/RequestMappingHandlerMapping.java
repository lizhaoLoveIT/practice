package cn.lizhaoloveit.springmvc.handlermapping.impl;

import cn.lizhaoloveit.springframework.beans.aware.BeanFactoryAware;
import cn.lizhaoloveit.springframework.beans.config.BeanDefinition;
import cn.lizhaoloveit.springframework.beans.factory.BeanFactory;
import cn.lizhaoloveit.springmvc.annotation.Controller;
import cn.lizhaoloveit.springmvc.annotation.RequestMapping;
import cn.lizhaoloveit.springmvc.handlermapping.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   17:43
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {

    // 建立URL 和 Controller 下的 Method 的映射关系
    private Map<String, HandlerMethod> urlHandlerMethodMap = new HashMap<>();

    private BeanFactory beanFactory;

    // 通过 beanFactoryAware 在初始化的时候注入 工厂
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void initMethod() {
        // 获取 spring 中所有的 bean
        List<String> beanNames = beanFactory.getBeanNamesByType(Object.class);
        for (String beanName : beanNames) {
            Map<String, BeanDefinition> beanDefinitions = beanFactory.getBeanDefiitions();
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> clazz = Class.forName(beanClassName);
                // 判断 clazz 是否带有 Controller 注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String url = requestMapping.value();
                            HandlerMethod handlerMethod = new HandlerMethod(method, beanFactory.getBean(beanName));
                            urlHandlerMethodMap.put(url, handlerMethod);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return urlHandlerMethodMap.get(uri);
    }
}
