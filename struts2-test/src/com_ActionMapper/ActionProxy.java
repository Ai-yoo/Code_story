package com_ActionMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Action代理类
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/12
 * Time:10:28
 */
public class ActionProxy implements InvocationHandler{
    public ActionAbstract actionAbstract;

    public ActionProxy(ActionAbstract actionAbstract) {
        this.actionAbstract = actionAbstract;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object=method.invoke(actionAbstract, args);
        return object;
    }
}
