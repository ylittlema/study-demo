package com.yjn.test.company;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

/**
 * <p>类的说明</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2015/12/18 YuanJunNan 创建
 *          <p>1.01 2015/12/18 修改者姓名 修改内容说明</p>
 */
public class XMLUtils {
    private static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    public static Element getRootElement(String xmlStr) throws UnsupportedEncodingException, DocumentException {
        if (xmlStr == null || "".equals(xmlStr)) {
            logger.info("xmlStr 字符串为空");
        }
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
        return document.getRootElement();
    }

    /**
     * 获取节点属性值
     *
     * @param ele
     * @param attrName
     * @return
     */
    public static String getAttributeByName(Element ele, String attrName) {
        return ele != null && ele.attribute(attrName) != null ? ele.attribute(attrName).getValue() : "";
    }

    /**
     * 获取节点中的内容
     *
     * @param ele
     * @return
     */
    public static String getElementText(Element ele) {
        String text = ele != null ? ele.getText() : "";
        return text;
    }
}
