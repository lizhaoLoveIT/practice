package cn.lizhaoloveit.springframework.beans.config;

import java.io.InputStream;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:14
 */
public interface Resource {
    boolean isCanRead(String location);
    InputStream getInputStream();
}
