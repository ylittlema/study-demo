package com.yjn.test.String;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/8/22  YuanJunNan 创建
 *          <p>1.01 2016/8/22 修改者姓名 修改内容说明</p>
 */
class Test {
    //不可变的String
    public static String appendStr(String s) {
        s += "bbb";
        return s;
    }

    //可变的StringBuilder
    public static StringBuilder appendSb(StringBuilder sb) {
        return sb.append("bbb");
    }

    public static void main(String[] args) {
        //String做参数
        String s = new String("aaa");
        String ns = Test.appendStr(s);
        System.out.println("String aaa >>> " + s.toString());
        //StringBuilder做参数
        StringBuilder sb = new StringBuilder("aaa");
        StringBuilder nsb = Test.appendSb(sb);
        System.out.println("StringBuilder aaa >>> " + sb.toString());




    }
}
