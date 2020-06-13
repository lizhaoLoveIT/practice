package cn.lizhaoloveit.springframework.beans.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-31
 * Time:   09:49
 */
public class DocumentReader {
    public static Document createDocument(InputStream inputStream) {
        Document document = null;
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
