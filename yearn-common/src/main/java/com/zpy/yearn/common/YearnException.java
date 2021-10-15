package com.zpy.yearn.common;

/**
 * Created by zpy on 2017/4/13.
 */
public class YearnException extends Exception{
    private String msg;
    private Exception e;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //Logger logger = LoggerFactory.getLogger(YearnException.class);
    public YearnException(String msg,Exception e){
        this.msg = msg;
        this.e = e;
    }
    public YearnException(String msg){
        this.msg = msg;
    }
}
