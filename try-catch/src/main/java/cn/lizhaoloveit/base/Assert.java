package cn.lizhaoloveit.base;

import cn.lizhaoloveit.base.exception.BaseException;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-07
 * Time:   00:33
 */
public interface Assert {
    /**
     * 创建异常
     * @param args
     * @return
     */
    BaseException exception(Object... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    BaseException exception(Throwable t, Object... args);

    /**
     * 断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * @param obj
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw exception(obj);
        }
    }

    /**
     * 断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * 异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
     * @param obj
     * @param args
     */
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw exception(args);
        }
    }
}
