package com.yjn.test.IO.file;

import java.io.File;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/6/7  YuanJunNan 创建
 *          <p>1.01 2016/6/7 修改者姓名 修改内容说明</p>
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("d:/");
        String [] dirList = file.list();
        for (String str :dirList){
            System.out.println(str);
        }
    }
}
