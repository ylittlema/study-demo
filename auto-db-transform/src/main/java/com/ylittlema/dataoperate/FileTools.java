package com.ylittlema.dataoperate;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * Created by yjn on 2017/8/25/0025.
 */
public class FileTools {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\316\\panel_title\\111");
        File[] subFils = file.listFiles();

        File ofile = new File("E:\\316\\panel_title\\layout_update.txt");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(ofile));

        for (File item : subFils) {
            String itenFileName = item.getName();
            String lId = itenFileName.split("_")[0];
            FileReader fr = new FileReader(item);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer jsonStr = new StringBuffer();
            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                jsonStr.append(lineStr);
            }
            String res = "update `layout_info` set `layout_json` = '" + JSON.parseObject(jsonStr.toString()).toJSONString() + "' where `layout_id` = " + lId + ";";
            out.write(res + "\n");
        }

        out.flush();
    }
}




























































