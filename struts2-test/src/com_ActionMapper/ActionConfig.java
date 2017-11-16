package com_ActionMapper;

import com_util.XMLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/12
 * Time:10:09
 */
public class ActionConfig {
    private XMLUtil xmlUtil = new XMLUtil();
    private Map<String, String> map = new HashMap<String, String>();

    /**
     * 读取struts.xml中关于action的配置交给ActionProxy
     */
    public Map readXml() {
        xmlUtil.doxml();
        String className = xmlUtil.actionMap.get("class");
        String actionName = xmlUtil.actionMap.get("method");
        System.out.println("Actioncomfig::"+className);
        System.out.println("Actionconfig::"+actionName);
        map.put("class", className);
        map.put("method", actionName);
        return map;
    }
}
