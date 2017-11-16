package com_Interceptor;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:13:32
 */
public class Interceptor3 extends Handler {
    @Override
    public void handleFeeRequest() {
        System.out.println("第三个拦截器");
        if (getSuccessor() != null) {
            getSuccessor().handleFeeRequest();
        }
    }
}
