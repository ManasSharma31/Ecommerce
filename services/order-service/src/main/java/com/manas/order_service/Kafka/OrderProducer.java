package com.manas.order_service.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String,OrderNotification> kafkaTemplate;

    public void sendMessage(OrderNotification orderNotification) {
        log.info("Sending message: <{}>",orderNotification);
        Message<OrderNotification> message = MessageBuilder
        .withPayload(orderNotification)
        .setHeader(KafkaHeaders.TOPIC, "order-topic") //this should be exactly the same name as that we created the kafka topic
        .setHeader("__TypeId__", "orderNotification") // Add type header for consumer deserialization
        .build();
        kafkaTemplate.send(message);
    }
}
