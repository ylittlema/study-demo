package com.yjn.test.hadoop.qunlive;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WCReducer extends Reducer< Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		//遍历value的list，进行累加求和
		for(IntWritable value:values){
			
			count += value.get();
		}
		
		//输出这一个单词的统计结果
		
		context.write(key, new IntWritable(count));

	}
}
