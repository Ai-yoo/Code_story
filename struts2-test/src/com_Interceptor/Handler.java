package com_Interceptor;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/13
 * Time:13:26
 */
public abstract class Handler {
    protected Handler successor = null;

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleFeeRequest();
}
