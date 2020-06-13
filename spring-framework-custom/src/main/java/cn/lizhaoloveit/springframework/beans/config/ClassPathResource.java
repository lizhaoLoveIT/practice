package cn.lizhaoloveit.springframework.beans.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:16
 */

@NoArgsConstructor
@Setter
@Getter
public class ClassPathResource implements Resource {
    private String location;
    @Override
    public boolean isCanRead(String location) {
        if (location == null || "".equals(location)) {
            return false;
        }
        if (location.startsWith("classpath:")) {
            this.location = location;
            return true;
        }
        return false;
    }
    @Override
    public InputStream getInputStream() {
        if (location == null || "".equals(location)) return null;
        String replace = location.replace("classpath:", "");
        return this.getClass().getClassLoader().getResourceAsStream(replace);
    }
}
