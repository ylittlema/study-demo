package com.ylittlema.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yjn on 2017/4/21/0021.
 */
public class ConsumerDemo {

    private final ConsumerConnector consumer;

    private ConsumerDemo() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "172.20.132.140:2181,172.20.132.141:2181,172.20.132.142:2181");
        //group 代表一个消费组
        props.put("group.id", "jd-group");
        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConfig config = new ConsumerConfig(props);
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(ProducerDemo.TOPIC, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(ProducerDemo.TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> item = it.next();
            System.out.println(item.key() + ":" + item.message());
        }

    }

    public static void main(String[] args) {
        new ConsumerDemo().consume();
    }
}

