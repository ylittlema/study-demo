package com.yjn.test.hadoop.qunlive;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * 4个泛型中，前两个是指定mapper输入数据的类型，KEYIN是输入的key的类型，VALUEIN是输入的value的类型 map 和 reduce
 * 的数据输入输出都是以 key-value对的形式封装的
 * 默认情况下，框架传递给我们的mapper的输入数据中，key是要处理的文本中一行的起始偏移量，这一行的内容作为value
 * 
 * @author sunyb
 * @since 2016-04-05
 * @version 1.0
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	// mapreduce框架每读一行数据就调用一次该方法
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		// 具体业务逻辑就写在这个方法体中，而且我们业务要处理的数据已经被框架传递进来，在方法的参数中 key-value
		// key 是这一行数据的起始偏移量, value 是这一行的文本内容

		try {
			// 将这一行的内容转换成string类型
			String line = value.toString();
			String[] words = line.split(" ");
			for (String word : words) {
				context.write(new Text(word), new IntWritable(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
