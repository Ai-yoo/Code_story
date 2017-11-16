package com_Interceptor;

/**
 * 拦截器
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:8:24
 */
public class Interceptor1 extends Handler{

    @Override
    public void handleFeeRequest() {
        System.out.println("第一个拦截器");
        if (getSuccessor() != null) {
            getSuccessor().handleFeeRequest();
        }
    }
}
