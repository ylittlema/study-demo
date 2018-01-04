package com.yjn.test.thread;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/5/23  YuanJunNan 创建
 *          <p>1.01 2016/5/23 修改者姓名 修改内容说明</p>
 */
public class ThreadTest extends Thread {
    public ThreadTest() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
    }
}
