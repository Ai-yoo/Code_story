package com_ActionMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 真实对象
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:8:13
 */
public class RealAction implements ActionAbstract {
    private Map<String, String> map = null;
    @Override
    public String doSomething(String username,String password,String usernamevalue,String passwordvalue) {
        ActionInvocation actionInvocation = new ActionInvocation();
        return actionInvocation.zhixing(username,password,usernamevalue,passwordvalue);
    }
}
