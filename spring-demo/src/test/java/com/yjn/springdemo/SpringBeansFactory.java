package com.yjn.springdemo;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>类的说明</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2015/12/9 YuanJunNan 创建
 *          <p>1.01 2015/12/9 修改者姓名 修改内容说明</p>
 */
public class SpringBeansFactory {
    Logger logger = Logger.getLogger(SpringBeansFactory.class);

    ApplicationContext cxp = null;
    BeanFactory bf;

    @Before
    public void before() {
        logger.info("Before....");
        cxp = new ClassPathXmlApplicationContext("applicationContext.xml");
//        bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
    }

    @After
    public void after() {
        logger.info("after....");
    }

    @Test
    public void test() {
        logger.info("SpringDeansFactory.....");
        logger.info(cxp.getBean("person"));
//        logger.info(bf.getBean("person"));
    }
}
