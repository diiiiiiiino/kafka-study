package com.study.kafka.service;

import com.study.kafka.dto.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class KafkaProduceServiceImpl implements KafkaProduceService {
    private static final String TOPIC_NAME = "music";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> jsonKafkaTemplate;

    @Override
    public void send(String message){
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    @Override
    public void sendJson(MyMessage message) {
        jsonKafkaTemplate.send(TOPIC_NAME, message);
    }

    @Override
    public void sendWithCallback(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed " + message + " due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Send " + message + " offset : " + result.getRecordMetadata().offset());
            }
        });
    }
}
