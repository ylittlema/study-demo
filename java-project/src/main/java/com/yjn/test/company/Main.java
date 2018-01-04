package com.yjn.test.company;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/10/22  YuanJunNan 创建
 *          <p>1.01 2016/10/22 修改者姓名 修改内容说明</p>
 */
public class Main {
    private static Map dataCacheMap = new HashMap();

    public static void main(String[] args) throws Exception {

        {

            File file = new File("E:\\316\\sql.txt");

            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
            FileReader reader = new FileReader("E:\\316\\tihuan.txt");
            StringBuffer error = new StringBuffer();
            BufferedReader buffer = new BufferedReader(reader);
            String action;
            int i = 0;
            while ((action = buffer.readLine()) != null) {
                String[] strArr = action.split("#");
                String id = strArr[0];
                JSONObject jb = JSON.parseObject(strArr[1]);
                JSONObject jd = jb.getJSONObject("params");
                String temp = jd.getString("id");
                if (temp.contains("_oqy_")) {
                    temp = temp.replace("oqy", "yinhe");
                } else if (temp.contains("_otx_")) {
                    temp = temp.replace("otx", "tx");
                }
                jd.put("id", temp);
                String last = jb.toJSONString();

                String sql = "UPDATE `video_content` SET `onclick` = '" + last + "' WHERE `v_content_id` = " + id + ";\n";

                out.write(sql);
//                System.out.println(sql);







                /*JSONObject jb = null;
                try {
                    jb = JSON.parseObject(action);
                } catch (Exception e) {
                    System.out.println(".......");
                }
                if (jb != null) {
                    JSONObject jd = jb.getJSONObject("params");
                    if (action.contains("_oqy_"))
                        System.out.println(jd.getString("id"));
                }*/
            }

            out.flush();
        }


    }

    public static String xmlFormat(String str) throws Exception {
        SAXReader reader = new SAXReader();
        // System.out.println(reader);
        // 注释：创建一个串的字符输入流
        StringReader in = new StringReader(str);
        Document doc = reader.read(in);
        // System.out.println(doc.getRootElement());
        // 注释：创建输出格式
        OutputFormat formater = OutputFormat.createPrettyPrint();
        //formater=OutputFormat.createCompactFormat();
        // 注释：设置xml的输出编码
        formater.setEncoding("utf-8");
        // 注释：创建输出(目标)
        StringWriter out = new StringWriter();
        // 注释：创建输出流
        XMLWriter writer = new XMLWriter(out, formater);
        // 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
        writer.write(doc);

        writer.close();
//        System.out.println(out.toString());
        // 注释：返回我们格式化后的结果
        return out.toString();
    }
}
