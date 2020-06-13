package cn.lizhaoloveit.springmvc.handlermapping.impl;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   17:48
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Method;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class HandlerMethod {
    private Method method;
    private Object handler;
}
