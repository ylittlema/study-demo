package com.yjn.test.hadoop.qunlive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

public class WCDirver {
    public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf = new Configuration();

        //使用下面的配置，或者不配置，可以在本地跑MR程序
        //如果classpath放了配置文件，则需要指定下面的配置
//        conf.set("mapreduce.framework.name", "192.168.3.16");
//        conf.set("fs.defaultFS", "file:///");
        conf.set("fs.defaultFS", "hdfs://192.168.3.16:9000");

        Job wcjob = Job.getInstance(conf);

        // 设置整个job所用的那些类在哪个jar包
        wcjob.setJarByClass(WCDirver.class);

        // 本job使用的mapper和reducer的类
        wcjob.setMapperClass(WCMapper.class);
        wcjob.setReducerClass(WCReducer.class);

        // 指定reduce的输出数据kv类型
        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(IntWritable.class);

        // 指定mapper的输出数据kv类型
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);

        // 指定要处理的输入数据存放路径
        FileInputFormat.setInputPaths(wcjob, new Path(args[0]));

        //下面的代码可以删除HDFS上已经存在的文件
        /*FileSystem fs = FileSystem.get(new URI("hdfs://192.168.116.10:9000/"),
                new Configuration(), "root");*/

        //下面的代码可以删除本地已经存在的文件
        FileSystem fs = FileSystem.get(conf);

        Path outPath = new Path(args[1]);
        if (fs.exists(outPath)) {
            //如果输出路径存在，先删除
            System.out.println(outPath + " exists, deleting......");
            fs.delete(outPath, true);
        }

        // 指定处理结果的输出数据存放路径
        FileOutputFormat.setOutputPath(wcjob, new Path(args[1]));

        // 将job提交给集群运行
        boolean result = wcjob.waitForCompletion(true);
        System.exit(result ? 0 : 1);
        //hadoop jar hdfs.jar com.sitech.hadoop.mr.wordcount.WCDirver /wdcount/input /wdcount/output
    }
}
