package cn.lizhaoloveit.springmvc.servlet;

import cn.lizhaoloveit.springframework.beans.factory.BeanFactory;
import cn.lizhaoloveit.springframework.beans.factory.DefaultListableBeanFactory;
import cn.lizhaoloveit.springmvc.handleradapter.HandlerAdapter;
import cn.lizhaoloveit.springmvc.handlermapping.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   14:06
 */
public class DispatchServlet extends AbstractHttpServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    @Override
    public void init(ServletConfig config) {
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new DefaultListableBeanFactory(contextConfigLocation);
        beanFactory.getBeanNamesByType(Object.class); // 初始化所有的bean
        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);
        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);
    }

    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        // 1. 根据请求获取对应的处理器
        Object handler = null;
        for (HandlerMapping handlerMapping : handlerMappings) {
            Object handlerTemp = handlerMapping.getHandler(request);
            if (handlerTemp != null) handler = handlerTemp;
        }
        // 2. 找到匹配的适配器
        HandlerAdapter ha = null;
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.support(handler)) {
                ha = handlerAdapter;
            }
        }

        try {
            ha.handleRequest(handler, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
