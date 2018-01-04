package com.yjn.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/11/24  YuanJunNan 创建
 *          <p>1.01 2016/11/24 修改者姓名 修改内容说明</p>
 */
public class HDFSDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        write2HDFSFile();
    }

    /**
     * 读取HDFS文件
     *
     * @throws IOException
     */
    public static void readFromHDFS() throws IOException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://10.10.2.153:9000"), new Configuration());
        FSDataInputStream in = fs.open(new Path("/data/test.txt"));
        IOUtils.copyBytes(in, System.out, 4096, false);
    }

    /**
     * 写入HDFS
     *
     * @throws IOException
     */
    public static void write2HDFSFile() throws IOException, InterruptedException {

        InputStream localFileIn = new FileInputStream(new File("E:\\ylittlema\\备份整理\\光宇\\打包20150607\\重构：改善既有代码的设计(中文版).pdf"));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://10.10.2.153:9000"), conf, "root");
        FSDataOutputStream out = fs.create(new Path("/data/test2.pdf"), true,4096,new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(localFileIn, out, 4096, false);

    }


}

