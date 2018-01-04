package com.yjn.test.IO.file.redis;

/**
 * Created by huzhang on 2017/3/21.
 */
public class MyLogger<T> {

    private static MyLogger logger;

    public synchronized static MyLogger getLogger() {
        if (null == logger) {
            logger = new MyLogger();
        }
        return logger;
    }

    public void error(String s, Exception ex) {
        System.out.println("错误名称：" + s + "-------------;info：" + ex.getMessage()+"\n"+ex.getLocalizedMessage());
    }
}
