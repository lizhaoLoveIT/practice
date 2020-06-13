package cn.lizhaoloveit.springframework.beans.converter;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-04-01
 * Time:   09:13
 */
public interface TypeConverter {
    boolean isType(Class<?> clazz);
    Object convert(String source);
}
