package cn.lizhaoloveit.springmvc.handleradapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   17:34
 */
public interface HandlerAdapter {
    boolean support(Object handler);
    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
