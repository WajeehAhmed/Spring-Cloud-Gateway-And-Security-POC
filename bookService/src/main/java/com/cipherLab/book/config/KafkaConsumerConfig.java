//package com.cipherLab.book.config;
//
//import com.cipherLab.book.dto.OrderEvent;
//import com.cipherLab.book.exception.KafkaErrorHandler;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.CommonErrorHandler;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Bean
//    public ConsumerFactory<String, OrderEvent> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapAddress);
//        configProps.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                "order-group");
//        configProps.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        configProps.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(configProps);
////        JsonDeserializer<OrderEvent> payloadJsonDeserializer = new JsonDeserializer<>();
////        payloadJsonDeserializer.trustedPackages("*");
////        return new DefaultKafkaConsumerFactory<>(
////                configProps,
////                new StringDeserializer(),
////                payloadJsonDeserializer
////        );
//
//    }
//
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, OrderEvent> kafkaListenerContainerFactory(
//            ConsumerFactory<String, OrderEvent> consumerFactory,
//            CommonErrorHandler commonErrorHandler
//    ) {
//        var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderEvent>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setCommonErrorHandler(commonErrorHandler);
//        return factory;
//    }
//    @Bean
//    CommonErrorHandler commonErrorHandler() {
//        return new KafkaErrorHandler();
//    }
//}