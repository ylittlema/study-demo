package com.ylittlema.dataoperate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.sun.xml.internal.bind.v2.util.ClassLoaderRetriever.getClassLoader;

public class JDBCTools {
    /**
     * 获取数据库连接的方法
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties pro = new Properties();
        InputStream inStream = getClassLoader().getResourceAsStream("db.properties");
        pro.load(inStream);

        String tvos6xTopicUrl = pro.getProperty("tvos6x-topic.url");
        String tvos6xTopicUsername = pro.getProperty("tvos6x-topic.username");
        String tvos6xTopicPassword = pro.getProperty("tvos6x-topic.password");
        String movieTopicUrl = pro.getProperty("movie-topic.url");
        String movieTopicUsername = pro.getProperty("movie-topic.username");
        String movieTopicPassword = pro.getProperty("movie-topic.password");
        String driverClass = pro.getProperty("driverClass");

        Connection connection = DriverManager.getConnection(tvos6xTopicUrl, tvos6xTopicUsername, tvos6xTopicPassword);
        return connection;
    }

}
