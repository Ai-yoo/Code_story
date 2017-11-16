package com_ActionMapper;

import com_util.XMLUtil;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/12
 * Time:9:02
 */
public class ActionMapper {
    private XMLUtil xmlUtil = new XMLUtil();
    public boolean result(String url) {
        int index = url.lastIndexOf("/");
        String namespace = url.substring(0, index);
        String name = url.substring(index+1);
        xmlUtil.doxml();
        System.out.println("map:"+xmlUtil.packetMap.get("namespace"));
        System.out.println("00"+xmlUtil.actionMap.get("name"));
        if (namespace.equals(xmlUtil.packetMap.get("namespace")) && name.equals(xmlUtil.actionMap.get("name"))) {
            System.out.println("jing");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ActionMapper a = new ActionMapper();
        a.result("/user/login");
    }
}
