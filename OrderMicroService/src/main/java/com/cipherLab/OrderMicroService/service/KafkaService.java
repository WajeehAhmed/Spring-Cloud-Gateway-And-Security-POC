//package com.cipherLab.OrderMicroService.service;
//
//import com.cipherLab.OrderMicroService.dto.OrderEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.KafkaException;
//import org.apache.kafka.common.errors.TopicAuthorizationException;
//import org.apache.kafka.common.errors.TopicExistsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//@Slf4j
//public class KafkaService {
//    @Autowired
//    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
//
//    @Async
//    public void sendMessage(String topicName, OrderEvent orderEvent) {
//        try {
//            CompletableFuture<SendResult<String, OrderEvent>> future = kafkaTemplate.send(topicName, orderEvent);
//            future.whenComplete((result, ex) -> {
//                if (ex == null) {
//                    log.info("Successfully posted Order Event on Topic : {} ", result.getRecordMetadata().topic());
//                } else {
//                    log.info("Failed to posted Order Event on Topic : {} ", topicName, ex);
//                }
//            });
//        } catch (KafkaException kafkaException) {
//            if (kafkaException instanceof TopicExistsException || kafkaException instanceof TopicAuthorizationException) {
//                log.error("Failed to post message due to invalid topic name | Message : {}", kafkaException.getMessage());
//                //TODO throw exception
//            }
//        } catch (Exception e) {
//            log.error("Failed to post message due to general exception : {}", e.getMessage());
//        }
//    }
//}
//
