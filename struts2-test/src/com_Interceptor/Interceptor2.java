package com_Interceptor;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:13:25
 */
public class Interceptor2 extends Handler {
    @Override
    public void handleFeeRequest() {
        System.out.println("第二个拦截器");
        if (getSuccessor() != null) {
            getSuccessor().handleFeeRequest();
        }
    }
}
