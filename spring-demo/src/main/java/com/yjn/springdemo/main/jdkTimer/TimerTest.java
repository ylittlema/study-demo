package com.yjn.springdemo.main.jdkTimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/5/10  YuanJunNan 创建
 *          <p>1.01 2016/5/10 修改者姓名 修改内容说明</p>
 *
 */

/**
 * Timer执行会有缺陷:
 * 1.如果存在多个线程，若其中某个线程因为某种原因而导致线程任务执行时间过长，超过了两个任务的间隔时间 会发生一些缺陷：
 * 2.如果TimerTask抛出RuntimeException，Timer会终止所有任务的运行
 * 用ScheduledExecutorService替代Timer 可以解决上面缺陷
 */
public class TimerTest {
    private Timer timer;
    public long start;

    public TimerTest() {
        this.timer = new Timer();
        start = System.currentTimeMillis();
    }

    public void timerOne() {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
                try {
                    Thread.sleep(4000);
                    //线程休眠3000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    public void timerTwo() {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
            }
        }, 3000);
    }

    public static void main(String[] args) throws Exception {
        TimerTest test = new TimerTest();
        test.timerOne();
        test.timerTwo();
    }
}
