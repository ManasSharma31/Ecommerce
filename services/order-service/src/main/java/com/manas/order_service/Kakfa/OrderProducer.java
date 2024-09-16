package com.manas.order_service.Kakfa;

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

    private final KafkaTemplate<String,OrderConfirmation> kafkaTemplate;

    public void sendMessage(OrderConfirmation orderConfirmation) {
        log.info("Sending message: <{}>",orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder
        .withPayload(orderConfirmation)
        .setHeader(KafkaHeaders.TOPIC, "order-topic") //this should be exactly the same name as that we created the kafka topic
        .build();
        kafkaTemplate.send(message);
    }
}
