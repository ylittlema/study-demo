package com.ylittlema.dataoperate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.*;

/**
 * Created by yjn on 2017/8/24/0024.
 */
public class PanelTitle {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
//        layoutJsonTool();
        update();
    }
    public static void layoutJsonTool() throws IOException {
        File file = new File("E:\\316\\panel_title\\111");
        File[] subFils = file.listFiles();

        File ofile = new File("E:\\316\\panel_title\\title_layout_2.txt");
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
            String res = lId + "#" + JSON.parseObject(jsonStr.toString()).toJSONString();
            out.write(res + "\n");
        }

        out.flush();
    }


    public static void update() throws IOException, ClassNotFoundException, SQLException {
        File file = new File("E:\\316\\panel_title\\146_2\\update_sql.txt");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
        FileReader reader = new FileReader("E:\\316\\panel_title\\146_2\\title_layout_2.txt");
        BufferedReader buffer = new BufferedReader(reader);
        String lineStr = "";
        int i = 0;

        while ((lineStr = buffer.readLine()) != null) {
            String[] tempArr = lineStr.split("#");
            String layoutId = tempArr[0];
            //将数据驱动程序类加载到内存中
            Class.forName(AutoDBTransFrom.driverClass);
            Connection conn = DriverManager.getConnection(AutoDBTransFrom.tvos6xTopicUrl, AutoDBTransFrom.tvos6xTopicUsername, AutoDBTransFrom.tvos6xTopicPassword);
            String sql = "SELECT `pannel_id` FROM `pannel_info` WHERE `layout_id` = " + layoutId + ";";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int index = 0;
                Integer pid = resultSet.getInt(1);
                System.out.println("panelId:"+pid);
                String sqlp = "select `pannel_v_content_rls_id`  from `pannel_video_content_rls` where `pannel_id` = " + pid + " order by `v_content_id`;";
                Statement statementp = conn.createStatement();
                ResultSet resultSetp = statementp.executeQuery(sqlp);
                while (resultSetp.next()) {
                    Integer pvcid = 0;
                    try {
                        StringBuffer updateSql = new StringBuffer("update `pannel_video_content_rls` set `v_content_position`='");
                        pvcid = resultSetp.getInt(1);
                        if (3983 == pvcid) {
                            System.out.println();
                        }

                        String layoutJsonStr = tempArr[1];
                        JSONObject jb = JSON.parseObject(layoutJsonStr);

                        JSONArray bjb = jb.getJSONArray("contents");
                        JSONObject item = bjb.getJSONObject(index);
                        index++;
                        String resizeStr = item.getString("resize");
                        String titleStr = item.getString("title_info");
                        item.remove("resize");
                        item.remove("title_info");
                        updateSql.append(item.toJSONString());
                        updateSql.append("',`block_title_position` = '");
                        if (titleStr != null && !"null".equals(titleStr)) {
                            updateSql.append(titleStr);
                        }else {
                            updateSql.append("");
                        }
                        updateSql.append("',`block_resize_position`='");
                        if (resizeStr != null && !"null".equals(resizeStr)) {
                            updateSql.append(resizeStr);
                        }else {
                            updateSql.append("");
                        }
                        updateSql.append("' where `pannel_v_content_rls_id` =");
                        updateSql.append(pvcid);
                        updateSql.append(";\n");
                        out.write(updateSql.toString());
                    } catch (Exception e) {
                        System.out.println(pvcid);
                    }
                }
            }
        }
        out.flush();
    }

}
