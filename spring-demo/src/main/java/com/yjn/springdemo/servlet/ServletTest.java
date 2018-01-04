package com.yjn.springdemo.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>类的说明</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/1/13 YuanJunNan 创建
 *          <p>1.01 2016/1/13 修改者姓名 修改内容说明</p>
 */
public class ServletTest extends HttpServlet {

    Logger log = Logger.getLogger(ServletTest.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost....");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet....");
    }
}
