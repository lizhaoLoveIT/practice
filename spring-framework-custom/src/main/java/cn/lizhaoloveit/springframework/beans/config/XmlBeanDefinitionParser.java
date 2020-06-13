package cn.lizhaoloveit.springframework.beans.config;

import cn.lizhaoloveit.springframework.beans.config.XmlBeanDefinitionDocumentReader;
import cn.lizhaoloveit.springframework.beans.factory.DefaultListableBeanFactory;
import cn.lizhaoloveit.springframework.beans.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-30
 * Time:   11:25
 */
public class XmlBeanDefinitionParser {
    public void loadBeanDefinations(DefaultListableBeanFactory beanFactory, Resource resource) {
        // 读取文件的配置信息
        InputStream inputStream = resource.getInputStream();
        Document document = DocumentReader.createDocument(inputStream);
        XmlBeanDefinitionDocumentReader reader = new XmlBeanDefinitionDocumentReader(beanFactory);
        reader.loadBeanDefinitions(document.getRootElement());
    }
}
