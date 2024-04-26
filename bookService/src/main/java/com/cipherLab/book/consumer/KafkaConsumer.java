//package com.cipherLab.book.consumer;
//
//import com.cipherLab.book.dto.OrderEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class KafkaConsumer {
//
//    @KafkaListener(topics = "OrderManagementTopic", groupId = "order-group")
//    public void receive(OrderEvent payload) {
////        var value = payload.value();
//        log.info("Value you are looking for : {} ", payload);
//        log.info("Kafka consuming completed");
//    }
//
//}
