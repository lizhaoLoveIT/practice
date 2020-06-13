package cn.lizhaoloveit.springframework.beans.config;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-31
 * Time:   10:21
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class PropertyValue {
    private String name;
    private Object value;
}
