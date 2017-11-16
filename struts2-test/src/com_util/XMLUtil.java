package com_util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/10
 * Time:17:44
 */
public class XMLUtil {
    private List<Attribute> packetList = new ArrayList<>();
    private List<Attribute> actionList = new ArrayList<>();
    private List<Attribute> resultList = new ArrayList<>();
    public Map<String,String> packetMap = new HashMap<String,String>();
    public Map<String,String> actionMap = new HashMap<String,String>();
    public Map<String,String> resultMap = new HashMap<String,String>();
    public void doxml() {
        File file = new File("E:\\IntelliJ IDEA\\struts2-test\\src\\struts.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            Element root = document.getRootElement();
            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element node = (Element) it.next();
                packetList = node.attributes();
                for (Iterator childs = node.elementIterator(); childs.hasNext(); ) {
                    Element nodes = (Element) childs.next();
                    actionList = nodes.attributes();
                    for (Iterator children = nodes.elementIterator(); children.hasNext(); ) {
                        Element nodess = (Element) children.next();
                        resultMap.put(nodess.attribute(0).getValue(), nodess.getText());
                    }
                }
            }
            for (Attribute att : packetList) {
                System.out.println(att.getName()+" "+att.getValue());
                packetMap.put(att.getName(), att.getValue());
            }
            for (Attribute att : actionList) {
                System.out.println(att.getName() + " " + att.getValue());
                actionMap.put(att.getName(), att.getValue());
            }
            System.out.println(packetMap.toString());
            System.out.println(actionMap.toString());
            System.out.println(resultMap.toString());
        }
        catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        XMLUtil x = new XMLUtil();
        x.doxml();
    }
}
