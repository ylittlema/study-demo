package com.yjn.springdemo;

import com.yjn.springdemo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/3/17  YuanJunNan 创建
 * <p>1.01 2016/3/17 修改者姓名 修改内容说明</p>
 */

/**
 * locations Spring 的配置文件
 * classes  指定创建ApplicationContext.的类
 * initializers  指定实例化ConfigurableApplicationContext.的类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class SpringMybatisTest {

    @Autowired
    private Person person;

    @Autowired(required = false)
    private SqlSessionFactoryBean sqlSessionFactory;

    @Test
    public void getBean() {
        System.out.println(sqlSessionFactory);
    }
}
