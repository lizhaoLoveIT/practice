package cn.lizhaoloveit.base.exception;

import cn.lizhaoloveit.base.exception.BaseException;
import org.springframework.util.StringUtils;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-07
 * Time:   00:33
 */
public interface AssertNotNull {

    /**
     * 创建异常
     * @param clz
     * @param count 第几个参数
     * @return
     */
    BaseException exception(Class clz, int count);

    /**
     * 创建异常
     * @param t
     * @param clz
     * @param count 第几个参数
     * @return
     */
    BaseException exception(Class clz, int count, Throwable t);

    /**
     * 断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * @param objs
     */
    default void assertNotNull(Object... objs) {
        int count = 1;
        for (Object obj : objs) {
            if (obj instanceof String) {
                if (StringUtils.isEmpty(obj)) throw exception(String.class, count);
            } else if (obj instanceof Integer) {
                if (obj == null) throw exception(Integer.class, count);
            } else if (obj instanceof Long) {
                if (obj == null) throw exception(Long.class, count);
            } else {
                if (obj == null) throw exception(Object.class, count);
            }
            count++;
        }
    }
}
