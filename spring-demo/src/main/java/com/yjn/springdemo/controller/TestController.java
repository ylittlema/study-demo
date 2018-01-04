package com.yjn.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/3/27  YuanJunNan 创建
 *          <p>1.01 2016/3/27 修改者姓名 修改内容说明</p>
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("meathod")
    public String testMeathod(){
       return "";
    }
}
