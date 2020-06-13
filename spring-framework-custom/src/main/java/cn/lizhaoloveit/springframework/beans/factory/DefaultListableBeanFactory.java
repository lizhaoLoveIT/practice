package cn.lizhaoloveit.springframework.beans.factory;

import cn.lizhaoloveit.springframework.beans.aware.Aware;
import cn.lizhaoloveit.springframework.beans.aware.BeanFactoryAware;
import cn.lizhaoloveit.springframework.beans.config.BeanDefinition;
import cn.lizhaoloveit.springframework.beans.config.ClassPathResource;
import cn.lizhaoloveit.springframework.beans.config.PropertyValue;
import cn.lizhaoloveit.springframework.beans.config.Resource;
import cn.lizhaoloveit.springframework.beans.config.RuntimeBeanReference;
import cn.lizhaoloveit.springframework.beans.config.TypeStringValue;
import cn.lizhaoloveit.springframework.beans.config.XmlBeanDefinitionParser;
import cn.lizhaoloveit.springframework.beans.converter.IntegerTypeConverter;
import cn.lizhaoloveit.springframework.beans.converter.StringTypeConverter;
import cn.lizhaoloveit.springframework.beans.converter.TypeConverter;
import cn.lizhaoloveit.springframework.beans.utils.ReflectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:08
 */

@NoArgsConstructor
@Setter
@Getter
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    private List<Resource> resources = new ArrayList<>();
    private Map<String, BeanDefinition> beanDefinitionIds = new HashMap<>();
    private Map<String, BeanDefinition> beanDefinitionClzs = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();
    private List<TypeConverter> typeConverters = new ArrayList<>();

    public DefaultListableBeanFactory(String location) {
        // 注册资源类(从哪里获取资源)
        registResource();
        // 注册类型转换器
        registConverters();
        // 不清楚 location 字符串到底代表的类路径下的 xml 或者是 绝对路径下的 xml 或者网络中的 xml
        Resource resource = getResource(location);
        // 获取了资源对象，根据资源对象创建 BeanDefinitions，此时需要个解析器
        XmlBeanDefinitionParser parser = new XmlBeanDefinitionParser();
        parser.loadBeanDefinations(this, resource);
    }

    private void registConverters() {
        typeConverters.add(new StringTypeConverter());
        typeConverters.add(new IntegerTypeConverter());
    }

    private Resource getResource(String location) {
        for (Resource resource : resources) {
            if (resource.isCanRead(location)) return resource;
        }
        return null;
    }

    private void registResource() {
        resources.add(new ClassPathResource());
    }
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionIds.put(beanName, beanDefinition);
    }
    @Override
    public void registerBeanDefinitionClz(String simpleName, BeanDefinition beanDefinition) {
        this.beanDefinitionClzs.put(simpleName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        Object instance = singletonObjects.get(beanName);
        if (instance != null) return instance;
        // 2.如果singletonObjects中已经没有包含我们要找的对象，那么根据传递过来的beanName参数去BeanDefinition集合中查找对应的BeanDefinition信息
        BeanDefinition beanDefinition = getBeanDefinitionIds().get(beanName);
        String beanClassName = beanDefinition.getBeanClassName();
        instance = createBeanInstance(beanClassName, null);
        setProperty(instance, beanDefinition);
        initBean(instance, beanDefinition);
        singletonObjects.put(beanName, instance);
        return instance;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        // 优化方案
        // 给对象起个名，在xml配置文件中，建立名称和对象的映射关系
        // 1.如果singletonObjects中已经包含了我们要找的对象，就不需要再创建了。
        String clazzSimpleName = clazz.getSimpleName();
        T instance = (T) singletonObjects.get(clazzSimpleName);
        if (instance != null) return instance;
        // 2.如果singletonObjects中已经没有包含我们要找的对象，那么根据传递过来的beanName参数去BeanDefinition集合中查找对应的BeanDefinition信息
        BeanDefinition beanDefinition = getBeanDefinitionClzs().get(clazzSimpleName);
        // 3.根据BeanDefinition中的信息去创建Bean的实例。
        String beanClassName = beanDefinition.getBeanClassName();
        // a)根据class信息包括里面的constructor-arg通过反射进行实例化
        instance = (T) createBeanInstance(beanClassName, null);
        // b)根据BeanDefinition中封装的属性信息集合去挨个给对象赋值。
        // 设置参数
        // 类型转换
        // 反射赋值
        setProperty(instance, beanDefinition);
        // c)根据initMethod方法去调用对象的初始化操作
        initBean(instance, beanDefinition);
        singletonObjects.put(clazzSimpleName, instance);
        return instance;
    }

    private <T> void initBean(T instance, BeanDefinition beanDefinition) {
        // 判断aware是不是instance实例的接口
        // 操作Aware接口
        if (instance instanceof Aware) {
            if (instance instanceof BeanFactoryAware) {
                ((BeanFactoryAware) instance).setBeanFactory(this);
            }
        }

        String initMethod = beanDefinition.getInitMethod();
        if (initMethod == null || initMethod.equals("")) return;
        ReflectUtils.invokeMethod(instance, initMethod, null);
    }

    private <T> void setProperty(T instance, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            Object trueValue = null;
            if (value instanceof TypeStringValue) {
                TypeStringValue typeStringValue = (TypeStringValue) value;
                String stringValue = typeStringValue.getValue();
                Class<?> clazz = typeStringValue.getClassType();
                // 使用类型转换器进行转换
                for (TypeConverter typeConverter : typeConverters) {
                    if (typeConverter.isType(clazz)) {
                        trueValue = typeConverter.convert(stringValue);
                    }
                }
            } else if (value instanceof RuntimeBeanReference) {
                RuntimeBeanReference reference = (RuntimeBeanReference) value;
                String ref = reference.getRef();
                trueValue = getBean(ref);
            }
            // 值类型转换完后 使用反射赋值
            ReflectUtils.setProperty(instance, name, trueValue);
        }
    }

    /**
     * 根据指定bean的类型，获取对应的类型和子类型对应的bean实例
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getBeansByType(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            for (BeanDefinition beanDefinition : beanDefinitionClzs.values()) {
                // 所有beandefinition中的bean的类型
                Class<?> clazz1 = Class.forName(beanDefinition.getBeanClassName());
                // clazz是否是clazz1的父类型
                if (clazz.isAssignableFrom(clazz1)) {
                    String beanName = beanDefinition.getBeanName();
                    list.add((T) getBean(beanName));
                }
            }

            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        try {
            List<String> result = new ArrayList<>();
            for (String beanName : beanDefinitionIds.keySet()) {
                BeanDefinition beanDefinition = beanDefinitionIds.get(beanName);

                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                // 判断type是否是clazz的父类
                if (type.isAssignableFrom(clazz)) {
                    result.add(beanName);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createBeanInstance(String beanClassName, Object... args) {
        return ReflectUtils.createObject(beanClassName, args);
    }

    @Override
    public Map<String, BeanDefinition> getBeanDefiitions() {
        return beanDefinitionIds;
    }
}
