package cn.lizhaoloveit.springmvc.handlermapping;

import javax.servlet.http.HttpServletRequest;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   17:34
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
