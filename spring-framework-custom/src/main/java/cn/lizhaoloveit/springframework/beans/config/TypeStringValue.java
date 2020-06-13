package cn.lizhaoloveit.springframework.beans.config;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-04-01
 * Time:   08:04
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class TypeStringValue {
    private String value;
    private Class<?> classType;
}
