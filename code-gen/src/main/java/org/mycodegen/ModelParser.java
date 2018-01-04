package org.mycodegen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.*;

public class ModelParser {
    public static List parse(InputStream is) {
        List models = new ArrayList();
        String packageName = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is, "UTF-8");
            Element root = doc.getDocumentElement();
            packageName = root.getAttribute("packageName");
            NodeList modelList = root.getElementsByTagName("model");
            for (int i = 0; i < modelList.getLength(); i++) {
                Element modelElement = (Element) modelList.item(i);
                ModelDefinition md = parseModel(modelElement);
                md.setPackageName(packageName);
                models.add(md);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    private static ModelDefinition parseModel(Element modelElement) {
        ModelDefinition md = new ModelDefinition();
        md.setClassName(modelElement.getAttribute("className"));
        if (modelElement.hasAttribute("tableName"))
            md.setTableName(modelElement.getAttribute("tableName"));
        md.setProperties(getMapFromModelElement(modelElement, "property"));
        md.setManyToOne(getMapFromModelElement(modelElement, "manyToOne"));
        md.setOneToMany(getMapFromModelElement(modelElement, "oneToMany"));
        md.setManyToMany(getMapFromModelElement(modelElement, "manyToMany"));
        md.setLabels(getLabelsFromModelElement(modelElement));
        md.setRequireds(getRequiredsFromModelElement(modelElement));
        md.setNotInUIs(getNotInUIsFromModelElement(modelElement));
        md.setInverses(getInversesFromModelElement(modelElement));
        return md;
    }

    private static Map getMapFromModelElement(Element modelElement,
                                              String mapType) {
        Map map = new LinkedHashMap();
        NodeList nodeList = modelElement.getElementsByTagName(mapType);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            map.put(ele.getAttribute("name"), ele.getAttribute("type"));
        }
        return map;
    }

    private static Map getLabelsFromModelElement(Element modelElement) {
        Map map = new LinkedHashMap();
        NodeList nodeList = modelElement.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            map.put(ele.getAttribute("name"), EncodeUtils.native2ascii(ele
                    .getAttribute("label")));
        }
        nodeList = modelElement.getElementsByTagName("manyToOne");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            map.put(ele.getAttribute("name"), EncodeUtils.native2ascii(ele
                    .getAttribute("label")));
        }
        nodeList = modelElement.getElementsByTagName("oneToMany");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            map.put(ele.getAttribute("name"), EncodeUtils.native2ascii(ele
                    .getAttribute("label")));
        }
        nodeList = modelElement.getElementsByTagName("manyToMany");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            map.put(ele.getAttribute("name"), EncodeUtils.native2ascii(ele
                    .getAttribute("label")));
        }
        return map;
    }

    private static Set getRequiredsFromModelElement(Element modelElement) {
        Set set = new HashSet();
        NodeList nodeList = modelElement.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            if ("true".equals(ele.getAttribute("required")))
                set.add(ele.getAttribute("name"));
        }
        return set;
    }

    private static Set getNotInUIsFromModelElement(Element modelElement) {
        Set set = new HashSet();
        NodeList nodeList = modelElement.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            if ("true".equals(ele.getAttribute("notInUI")))
                set.add(ele.getAttribute("name"));
        }
        return set;
    }

    private static Set getInversesFromModelElement(Element modelElement) {
        Set set = new HashSet();
        NodeList nodeList = modelElement.getElementsByTagName("manyToMany");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            if ("true".equals(ele.getAttribute("inverse")))
                set.add(ele.getAttribute("name"));
        }
        return set;
    }

}
