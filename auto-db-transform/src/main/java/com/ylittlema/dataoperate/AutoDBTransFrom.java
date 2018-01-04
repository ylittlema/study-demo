package com.ylittlema.dataoperate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import static com.sun.xml.internal.bind.v2.util.ClassLoaderRetriever.getClassLoader;

/**
 * Hello world!
 */
public class AutoDBTransFrom {

    static String tvos6xTopicUrl = null;
    static String tvos6xTopicUsername = null;
    static String tvos6xTopicPassword = null;
    static String movieTopicUrl = null;
    static String movieTopicUsername = null;
    static String movieTopicPassword = null;
    static String driverClass = null;

    static {
        try {
            Properties pro = new Properties();
            InputStream inStream = getClassLoader().getResourceAsStream("db.properties");
            pro.load(inStream);
            tvos6xTopicUrl = pro.getProperty("tvos6x-topic.url");
            tvos6xTopicUsername = pro.getProperty("tvos6x-topic.username");
            tvos6xTopicPassword = pro.getProperty("tvos6x-topic.password");
            movieTopicUrl = pro.getProperty("movie-topic.url");
            movieTopicUsername = pro.getProperty("movie-topic.username");
            movieTopicPassword = pro.getProperty("movie-topic.password");
            driverClass = pro.getProperty("driverClass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transFromData() throws ClassNotFoundException, SQLException, IOException {
        File file = new File("D:\\auto-topic\\6x.txt");
        FileOutputStream out = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);

        //将数据驱动程序类加载到内存中
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(tvos6xTopicUrl, tvos6xTopicUsername, tvos6xTopicPassword);
        String sql = "SELECT pgpr.`pannel_group_id`,vc.`v_content_id`,pin.`pannel_name`,pin.`pannel_title`,vc.`title`,`extra_value_1`,pin.`pannel_resource`,vc.`onclick`\n" +
                "FROM\n" +
                "`pannel_group_pannel_rls` pgpr\n" +
                "JOIN `pannel_info` pin ON (pgpr.`pannel_id` = pin.`pannel_id` AND pgpr.`relation_status` =1) \n" +
                "JOIN `pannel_video_content_rls` pvcr ON (pin.`pannel_id` = pvcr.`pannel_id`)\n" +
                "JOIN  `video_content` vc ON(vc.`v_content_id` = pvcr.`v_content_id`)\n" +
                "WHERE pin.`pannel_type` =3 ;";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int i = 1;
        Set<String> test = new LinkedHashSet<String>();
        while (resultSet.next()) {
//            System.out.println(">>>>>:" + i++);
            String topicId = resultSet.getString(1);
            String vcId = resultSet.getString(2);
            String topicName = resultSet.getString(3);
            String topicSubName = resultSet.getString(4);
            String videoName = resultSet.getString(5);
            String videoSorceAndId = resultSet.getString(6);
            String topicResoure = resultSet.getString(7);
            String action = resultSet.getString(8);
            String vid = null;
            String vs = null;
            try {
                JSONObject jb = JSON.parseObject(action);
                String temp = jb.getJSONObject("params").getString("id");
                if (!temp.startsWith("_"))
                    temp = "_" + temp;

                String[] tempArr = temp.split("_");
                vs = tempArr[1];
                vid = tempArr[2].trim();
                if ("vl".equals(vs)) {
                    vs = "voole";
                } else if ("tx".equals(vs)) {
                    vs = "tencent";
                }
            } catch (Exception e) {
                System.out.println("topicId:" + topicId + ",videoName:" + videoName + ",vcId:" + vcId);
                vid = "";
            }
//            String temp = "6x_" + topicResoure + "_" + topicId + "#" + topicName + "#" + topicSubName + "#" + videoId + "#" + videoName + "#" + videoSource;

            topicResoure = topicResoure.replace("o_", "");

            if ("iqiyi".equals(topicResoure)) {
                topicResoure = "yinhe";
                test.add("6x_" + topicId);
            }
            String temp = "6x_" + topicId + "#" + topicName + "#" + topicSubName + "#" + vid + "#" + videoName + "#" + vs + "#" + topicResoure;


            bw.write(temp + "\n");

        }
        String aa = test.toString();
        System.out.println(aa);
        bw.close();
        osw.close();
        out.close();
    }

    private void transFromData2() throws ClassNotFoundException, SQLException, IOException {

        File file = new File("D:\\auto-topic\\5x.txt");
        FileOutputStream out = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        //将数据驱动程序类加载到内存中
        Class.forName(driverClass);
        Connection conn2 = DriverManager.getConnection(movieTopicUrl, movieTopicUsername, movieTopicPassword);
        String sql2 = "SELECT DISTINCT (gc.content_value) , rtb.topic_id ,rtb.topic_title,gc.title,gc.sub_title,gc.platform_id ,rtb.`topic_source`\n" +
                "FROM  `res_topic_base` rtb  JOIN `res_topic_content_relation` rtcr ON (rtb.`topic_id` = rtcr.`topic_id`) " +
                "JOIN `general_content` gc ON(gc.`content_id` = rtcr.`content_id`);";

        Statement statement = conn2.createStatement();
        ResultSet resultSet = statement.executeQuery(sql2);
        int i = 1;
        while (resultSet.next()) {
            String topicId = resultSet.getString(2);
            String topicName = resultSet.getString(3);
            String topicSubName = "";
            String videoName = resultSet.getString(4);
            String videoSource = resultSet.getString(6);
            String videoId = resultSet.getString(1).trim();
            String topicResoure = resultSet.getString(7);
            String temp = "5x_" + topicId + "#" + topicName + "#" + topicSubName + "#" + videoId + "#" + videoName + "#" + videoSource + "#" + topicResoure;
            bw.write(temp + "\n");
            System.out.println(">>>>>:" + i++);
        }
        bw.close();
        osw.close();
        out.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        AutoDBTransFrom autoDBTransFrom = new AutoDBTransFrom();
        autoDBTransFrom.transFromData();
        autoDBTransFrom.transFromData2();
    }
}
