package cn.lizhaoloveit.springframework.beans.config;

import cn.lizhaoloveit.springframework.beans.factory.DefaultListableBeanFactory;
import cn.lizhaoloveit.springframework.beans.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-31
 * Time:   09:56
 */
public class XmlBeanDefinitionDocumentReader {
    private DefaultListableBeanFactory beanFactory;
    public XmlBeanDefinitionDocumentReader(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public void loadBeanDefinitions(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // 获取标签名称
            String name = element.getName();
            // 判断是否为 bean标签
            if (name.equals("bean")) {
                parseBeansElement(element);
            } else {
                parseCustomElement(element);
            }
        }
        // 2.将bean信息封装到BeanDefinition对象中
        // 对bean标签解析解析
        // class信息
        // id信息
        // init-method信息
        // property标签信息----PropertyValue对象（name和value）
        // name信息
        // value信息
        // value属性---属性值、属性类型（属性赋值的时候，需要进行类型转换）TypedStringValue
        // ref属性---RuntimeBeanReference(bean的名称)---根据bean的名称获取bean的实例，将获取到的实例赋值该对象
        // 3.再将BeanDefinition放入beanDefinations集合对象中
    }

    /**
     * bean element
     *
     * @param element beanElement
     */
    private void parseBeansElement(Element element) {
        if (element == null) return;
        try {
            // 获取 id class init-method
            String id = element.attributeValue("id");
            String name = element.attributeValue("name");
            String clzName = element.attributeValue("class");
            Class<?> clzType = Class.forName(clzName);
            String initMethod = element.attributeValue("init-method");
            // 保存到 BeanDefinition 类中
            String beanName = id == null ? name : id;
            beanName = beanName == null ? clzType.getSimpleName() : beanName;
            BeanDefinition beanDefinition = new BeanDefinition(clzName, beanName);
            beanDefinition.setInitMethod(initMethod);
            // 获取 property 标签
            List<Element> propertyElements = element.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }
            // 注册 BeanDefinition 信息
            registerBeanDefinition(beanName, beanDefinition);
            registerBeanDefinitionClass(clzType.getSimpleName(), beanDefinition);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if (propertyElement == null) return;
        // 获取 name 属性
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String ref = propertyElement.attributeValue("ref");
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) return;

        PropertyValue propertyValue = null;
        if (value != null && !value.equals("")) {
            // 不是引用类型，因为 xml 中的 value 是String 类型，而成员变量的类型可能是各种各样的，所以要存储类型
            Class<?> classType = ReflectUtils.getTypeByFieldName(beanDefinition.getBeanClassName(), name);
            TypeStringValue typeStringValue = new TypeStringValue(value, classType);
            propertyValue = new PropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(propertyValue);
        } else if (ref != null && !ref.equals("")) {
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            propertyValue = new PropertyValue(name, reference);
            beanDefinition.addPropertyValue(propertyValue);
        } else return;
    }
    private void parseCustomElement(Element element) {
    }
    private void registerBeanDefinitionClass(String simpleName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinitionClz(simpleName, beanDefinition);
    }
    private void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
