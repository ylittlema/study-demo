package com.ylittlema.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by yjn on 2017/4/21/0021.
 */
public class ProducerDemo {

    private final Producer<String, String> producer;
    public final static String TOPIC = "TVOS60-POLICY-ALARM";

    private ProducerDemo() {
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "172.20.132.140:9092,172.20.132.141:9092,172.20.132.142:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        //request.required.acks
        //0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7). This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
        //1, which means that the producer gets an acknowledgement after the leader replica has received the data. This option provides better durability as the client waits until the server acknowledges the request as successful (only messages that were written to the now-dead leader but not yet replicated will be lost).
        //-1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data. This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
        props.put("request.required.acks", "1");
        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce() {
        for (int i = 0; i < 100; i++) {
            String key = "model-chip";
            String data = String.valueOf(i);
            producer.send(new KeyedMessage<String, String>(TOPIC, key, data));
            System.out.println("produce..." + i);
        }
        producer.close();
    }

    public static void main(String[] args) {
        new ProducerDemo().produce();
    }

}
