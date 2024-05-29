package com.study.kafka.consumer;

import JavaSessionize.avro.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.kafka.dto.MyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final String TOPIC_NAME = "music";

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME)
    public void listenMessage(ConsumerRecord<String, Music> record) {
        try{
            Music music = record.value();
            System.out.println(">>>" + music.getTitle() + "," + music.getSinger());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
