package com_ActionMapper;

import com_Interceptor.Handler;
import com_Interceptor.Interceptor1;
import com_Interceptor.Interceptor2;
import com_Interceptor.Interceptor3;
import com_util.XMLUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 调用拦截器，管理拦截器
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:13:34
 */
public class ActionInvocation {
    private Handler h1 = new Interceptor1();
    private Handler h2 = new Interceptor2();
    private Handler h3 = new Interceptor3();
    private XMLUtil xmlUtil = new XMLUtil();
    private Map<String,String> map = null;
    public String zhixing(String username,String password,String usernamevalue,String passwordvalue) {
        h3.setSuccessor(h2);
        h2.setSuccessor(h1);
        h3.handleFeeRequest();
        ActionConfig actionConfig = new ActionConfig();
        map = actionConfig.readXml();
        try {
            System.out.println("class:::::"+map.toString());
            Class clazz=Class.forName(map.get("class"));
            Object object = clazz.newInstance();
            Method method = clazz.getMethod(map.get("method"), null);
            method.invoke(object, null);
            Method method1 = clazz.getMethod(getSet(username), String.class);
            Method method2 = clazz.getMethod(getSet(password), String.class);
            method1.invoke(object,usernamevalue);
            method2.invoke(object,passwordvalue);
            Method method3 = clazz.getMethod("login", null);
            String string=(String)method3.invoke(object, null);
            return string;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getSet(String str) {

        return "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
