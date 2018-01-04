package com.yjn.test.thread;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/5/24  YuanJunNan 创建
 *          <p>1.01 2016/5/24 修改者姓名 修改内容说明</p>
 */
public class RunableTest implements Runnable {
    public RunableTest(){
        System.out.println("执行Runable的方法:"+Thread.currentThread().getName());
    }

    public void run() {
        System.out.println("执行Runable的方法:"+Thread.currentThread().getName());
    }
}
