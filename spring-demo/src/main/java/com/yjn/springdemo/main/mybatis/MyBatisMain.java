package com.yjn.springdemo.main.mybatis;

import com.yjn.springdemo.mapper.SysUserMapper;
import com.yjn.springdemo.model.SysUserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/12/13/0013  YuanJunNan 创建
 *          <p>1.01 2016/12/13/0013 修改者姓名 修改内容说明</p>
 */
public class MyBatisMain {
    public static void main(String[] args) {
        try {
            String conf = "MyBatisConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(conf);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession();
            SysUserMapper sum = session.getMapper(SysUserMapper.class);
            SysUserEntity user = sum.getUserById(1);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
