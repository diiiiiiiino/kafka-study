package com.study.kafka.service;

import JavaSessionize.avro.Music;
import com.study.kafka.dto.MyMessage;

public interface KafkaProduceService {
    void send(String message);
    void sendJson(MyMessage message);
    void sendWithCallback(String message);
    void sendAvro(Music message);
}
