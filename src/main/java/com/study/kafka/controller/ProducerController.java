package com.study.kafka.controller;

import com.study.kafka.dto.MyMessage;
import com.study.kafka.service.KafkaProduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaProduceService kafkaProduceService;

    @PostMapping("/publish")
    public String publish(@RequestBody String message){
        kafkaProduceService.send(message);
        return "published a message : " + message;
    }

    @PostMapping("/publish2")
    public String publish2(@RequestBody String message){
        kafkaProduceService.sendWithCallback(message);
        return "published a message : " + message;
    }

    @PostMapping("/publish3")
    public String publish3(@RequestBody MyMessage message){
        kafkaProduceService.sendJson(message);
        return "published a message : " + message.getName() + "," + message.getMessage();
    }
}
