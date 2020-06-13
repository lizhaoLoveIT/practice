package cn.lizhaoloveit.springmvc.handleradapter.impl;

import cn.lizhaoloveit.springmvc.annotation.ResponseBody;
import cn.lizhaoloveit.springmvc.handleradapter.HandlerAdapter;
import cn.lizhaoloveit.springmvc.handlermapping.impl.HandlerMethod;
import cn.lizhaoloveit.springmvc.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   17:44
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Object object = handlerMethod.getHandler();
        // 获取方法参数
        Map<String, String[]> paramMap = request.getParameterMap();
        List<Object> args = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            // 获取的参数名称如果不做特殊处理，获取的是 arg0 arg1
            String name = parameter.getName();
            Class<?> type = parameter.getType();
            String[] value = paramMap.get(name);
            if (value == null) {
                args.add(null);
                continue;
            }
            if (type == Integer.class) {
                args.add(Integer.valueOf(value[0]));
            } else if (type == String.class) {
                args.add(value[0]);
            }
        }
        Object[] argsObj = args.toArray();

        // 执行处理方法
        Object returnValue = method.invoke(object, argsObj);

        // 处理返回值
        if (method.isAnnotationPresent(ResponseBody.class)) {
            if (returnValue instanceof String) {
                response.setContentType("text/plain;charset=utf8");
                response.getWriter().write(returnValue.toString());
            } else if (returnValue instanceof Map) {
                response.setContentType("application/json;charset=utf8");
                String s = JsonUtils.object2Json(returnValue);
                response.getWriter().write(s);
            }
        } else {
            // 展示视图
        }
    }
}
