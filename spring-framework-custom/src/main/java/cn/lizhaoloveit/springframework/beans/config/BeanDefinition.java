package cn.lizhaoloveit.springframework.beans.config;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-31
 * Time:   10:19
 */
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BeanDefinition {
    private String beanName;        // bean的标识(可能是id，也可能是根据类型命名)
    private String beanClassName;   // bean的Class权限名
    private String initMethod;      // 初始化方法
    private List<PropertyValue> propertyValues = new ArrayList<>(); // bean 中包含哪些属性
    public BeanDefinition(String clzName, String beanName) {
        this.beanClassName = clzName;
        this.beanName = beanName;
    }
    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValues.add(propertyValue);
    }
}
